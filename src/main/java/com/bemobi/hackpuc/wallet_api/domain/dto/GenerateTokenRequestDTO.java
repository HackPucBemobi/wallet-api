package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Data
public class GenerateTokenRequestDTO {

    @Expose
    public String customerName;

    @Expose
    public String cardNumber;

    @Expose
    public String holder;

    @Expose
    public String expirationDate;

    @Expose
    public String brand;

}
