package com.bemobi.hackpuc.wallet_api.domain.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by alessandro.santos on 7/21/17.
 */
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class CreditCardDTO {

    @SerializedName("CardNumber")
    @Expose
    private String cardNumber;
    @SerializedName("Holder")
    @Expose
    private String holder;
    @SerializedName("ExpirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("SaveCard")
    @Expose
    private Boolean saveCard;
    @SerializedName("Brand")
    @Expose
    private String brand;
    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
}
