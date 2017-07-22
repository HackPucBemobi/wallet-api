package com.bemobi.hackpuc.wallet_api.domain.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * Created by alessandro.santos on 7/21/17.
 */

@Data
public class LinkDTO {

    @SerializedName("Method")
    @Expose
    private String method;
    @SerializedName("Rel")
    @Expose
    private String rel;
    @SerializedName("Href")
    @Expose
    private String href;
}
