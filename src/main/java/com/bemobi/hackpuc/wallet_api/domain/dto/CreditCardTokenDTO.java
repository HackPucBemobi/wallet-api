package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Data
public class CreditCardTokenDTO {
    @Expose
    private Long id;
    @Expose
    private String cardNumber;

    @Expose
    private String holder;

    @Expose
    private String expirationDate;
    @Expose
    private String brand;

}
