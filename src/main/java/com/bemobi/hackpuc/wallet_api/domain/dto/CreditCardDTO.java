package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/22/17.
 */
@Data
public class CreditCardDTO {
    @Expose
    private Long id;
    @SerializedName("cardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("holder")
    @Expose
    private String holder;
    @SerializedName("expirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("securityCode")
    @Expose
    private String securityCode;
    @SerializedName("cardToken")
    @Expose
    private String cardToken;

}
