package com.bemobi.hackpuc.wallet_api.controller;

import com.bemobi.hackpuc.wallet_api.domain.Costumer;
import com.bemobi.hackpuc.wallet_api.domain.CreditCard;
import com.bemobi.hackpuc.wallet_api.repository.CostumerRepository;
import com.bemobi.hackpuc.wallet_api.repository.CreditCardRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.plugin.javascript.navig.Array;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hcfonseca on 7/21/17.
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController {

    final CostumerRepository costumerRepository;

    final CreditCardRepository creditCardRepository;

    @Inject
    public RegisterController(CostumerRepository costumerRepository, CreditCardRepository creditCardRepository) {
        this.costumerRepository = costumerRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @PostMapping(value = "/costumer/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Costumer> create(@PathVariable String name) {
        Costumer costumer = new Costumer();
        costumer.setName(name);
        Costumer saved = costumerRepository.save(costumer);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping(value = "/costumer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Costumer>> findAll() {
        final List<Costumer> resultList = new ArrayList<>();
        final Iterable<Costumer> all = costumerRepository.findAll();
        all.forEach(resultList::add);
        return ResponseEntity.ok().body(resultList);
    }

    @GetMapping(value = "/costumer/{idFinger}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Costumer> findCostumersByFinger(@PathVariable Integer idFinger) {

        Costumer costumer = new Costumer();
        costumer = costumerRepository.findByFingerId(idFinger);

        if (costumer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(costumer);
        }
    }

}
