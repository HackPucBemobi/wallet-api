package com.bemobi.hackpuc.wallet_api.client;

import com.bemobi.hackpuc.wallet_api.domain.dto.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Component
public class PayClient {

    @Value("${application.pay.host}")
    private String host;

    private final String TOKEN_PATH = "token";

    private final String TOKEN_PAYMENT_PATH = "token/payment";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public Result doTransaction(PaymentRequestDTO paymentRequest) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", paymentRequest.getMerchantId());
        headers.set("MerchantKey", paymentRequest.getMerchantKey());

        HttpEntity<Result> entity = new HttpEntity(paymentRequest, headers);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + TOKEN_PAYMENT_PATH, entity, String.class);

        return gson.fromJson(responseEntity.getBody(), Result.class);
    }

    public List<CreateCustomerResponseDTO> generateToken(CreateCustomerRequestDTO customerDTO, String merchantId, String merchantKey) {

        List<CreateCustomerResponseDTO> responses = new ArrayList<CreateCustomerResponseDTO>();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("MerchantId", merchantId);
        headers.set("MerchantKey", merchantKey);

        for (CreditCardDTO item: customerDTO.getCreditCards()) {

            GenerateTokenRequestDTO token = new GenerateTokenRequestDTO();

            token.setCustomerName(customerDTO.getName());
            token.setCardNumber(item.getCardNumber());
            token.setHolder(customerDTO.getName());
            token.setExpirationDate(item.getExpirationDate());
            token.setBrand(item.getBrand());

            HttpEntity<Result> entity = new HttpEntity(token, headers);
            final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + TOKEN_PATH, entity,
                        String.class);

            CreateCustomerResponseDTO con = gson.fromJson(responseEntity.getBody(), CreateCustomerResponseDTO.class);
            con.setBrand(item.getBrand());
            con.setSecurityCode(item.getSecurityCode());
            responses.add(con);
        }

        return responses;
    }

}
