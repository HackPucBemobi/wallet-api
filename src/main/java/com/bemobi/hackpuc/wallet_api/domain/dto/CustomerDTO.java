package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class CustomerDTO {

    @SerializedName("Name")
    @Expose
    private String name;
}
