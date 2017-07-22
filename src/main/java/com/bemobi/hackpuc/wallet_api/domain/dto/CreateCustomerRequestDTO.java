package com.bemobi.hackpuc.wallet_api.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Data
public class CreateCustomerRequestDTO {

    private String name;

    private String password;

    private Integer idFinger;

    private List<CreditCardDTO> creditCards;

}
