package com.bemobi.hackpuc.wallet_api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by alessandro.santos on 7/22/17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    private String name;

    private String password;

    private Integer fingerId;

    //private List<CreditCardDTO> creditCards;
    private List<CreditCardTokenDTO> creditCards;
}
