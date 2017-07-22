package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/22/17.
 */

@Data
public class CustomerDTO {
    @SerializedName("name")
    @Expose
    private String name;
}
