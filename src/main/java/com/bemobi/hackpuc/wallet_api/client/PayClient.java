package com.bemobi.hackpuc.wallet_api.client;

import com.bemobi.hackpuc.wallet_api.domain.dto.PaymentRequestDTO;
import com.bemobi.hackpuc.wallet_api.domain.dto.Result;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Component
public class PayClient {

    @Value("${application.pay.host}")
    private String host;

    private final String PAY_PATH = "payment";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public Result doTransaction(PaymentRequestDTO paymentRequest) {

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Result> entity = new HttpEntity(paymentRequest, headers);

        final ResponseEntity<String> responseEntity = restTemplate.postForEntity(host + PAY_PATH, entity, String.class);

        return gson.fromJson(responseEntity.getBody(), Result.class);
    }

}
