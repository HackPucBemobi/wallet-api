package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by hcfonseca on 7/22/17.
 */

@Data
public class CreateCustomerResponseDTO {

    @SerializedName("cardToken")
    @Expose
    private String cardToken;

    @Expose
    private String brand;

    @Expose
    private String securityCode;

}
