package com.bemobi.hackpuc.wallet_api.controller;

import com.bemobi.hackpuc.wallet_api.client.PayClient;
import com.bemobi.hackpuc.wallet_api.domain.CreditCard;
import com.bemobi.hackpuc.wallet_api.domain.Customer;
import com.bemobi.hackpuc.wallet_api.domain.dto.*;
import com.bemobi.hackpuc.wallet_api.repository.CreditCardRepository;
import com.bemobi.hackpuc.wallet_api.repository.CustomerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by hcfonseca on 7/21/17.
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    final CustomerRepository customerRepository;

    final CreditCardRepository creditCardRepository;

    final PayClient payClient;

    @Inject
    public RegisterController(CustomerRepository costumerRepository,
                              CreditCardRepository creditCardRepository,
                              PayClient payClient) {
        this.customerRepository = costumerRepository;
        this.creditCardRepository = creditCardRepository;
        this.payClient = payClient;
    }

    @PostMapping(value = "/costumer/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> create(@PathVariable String name) {
        Customer customer = new Customer();
        customer.setName(name);
        Customer saved = customerRepository.save(customer);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping(value = "/costumer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> findAll() {
        final List<Customer> resultList = new ArrayList<>();
        final Iterable<Customer> all = customerRepository.findAll();
        all.forEach(resultList::add);
        return ResponseEntity.ok().body(resultList);
    }

    @GetMapping(value = "/customer/{idFinger}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> findCostumersByFinger(@PathVariable Integer idFinger) {

        Customer custumer = new Customer();
        custumer = customerRepository.findByFingerId(idFinger);

        if (custumer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(custumer);
        }
    }

    @PostMapping(value = "/creditCard/{idCreditCard}/transaction/{value}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Result> doTransaction(@PathVariable Long idCreditCard,
                                                @PathVariable Integer value,
                                                @RequestHeader("MerchantId") String merchantId,
                                                @RequestHeader("MerchantKey") String merchantKey) {


        CreditCard creditCard = new CreditCard();
        creditCard = creditCardRepository.findById(idCreditCard);

        CustomerDTO customer = new CustomerDTO();
        customer.setName(creditCard.getCustomer().getName());

        CreditCardDTO ccDTO = new CreditCardDTO();
        ccDTO.setCardToken(creditCard.getCardToken());
        ccDTO.setBrand(creditCard.getBrand());
        ccDTO.setSecurityCode(creditCard.getSecurityCode());

        PaymentDTO payment = new PaymentDTO();
        payment.setType("CreditCard");
        payment.setAmount(value);
        payment.setInstallments(1);
        payment.setSoftDescriptor("123456789ABCD");
        payment.setCreditCard(ccDTO);

        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
        paymentRequest.setMerchantId(merchantId);
        paymentRequest.setMerchantKey(merchantKey);
        paymentRequest.setMerchantOrderId(UUID.randomUUID().toString());
        paymentRequest.setCustomer(customer);
        paymentRequest.setPayment(payment);


        Result result = payClient.doTransaction(paymentRequest);

        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(result);
        }
    }

    @PostMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCostumerWithCreditCards(
                                                              @RequestBody CreateCustomerRequestDTO customerDTO,
                                                              @RequestHeader("MerchantId") String merchantId,
                                                              @RequestHeader("MerchantKey") String merchantKey
                                                              ) throws URISyntaxException{

        List<CreateCustomerResponseDTO> response = payClient.generateToken(customerDTO, merchantId, merchantKey);

        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPassword(customerDTO.getPassword());
        customer.setFingerId(customerDTO.getIdFinger());

        List<CreditCard> creditCards = response
                .stream()
                .map(item -> new CreditCard(null, item.getCardToken(), item.getBrand(), customer, item.getSecurityCode()))
                .collect(Collectors.toList());

        customer.setCreditCards(creditCards);

        customerRepository.save(customer);
        return ResponseEntity.created(new URI("/costumer")).body(customer);
    }

}
