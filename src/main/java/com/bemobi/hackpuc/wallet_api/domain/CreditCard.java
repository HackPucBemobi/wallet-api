package com.bemobi.hackpuc.wallet_api.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by hcfonseca on 7/21/17.
 */

@Entity
@Data
@Table(name="credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    private String holder;

    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "save_card")
    private Boolean saveCard;

    private String brand;

    @Column(name = "security_code")
    private String securityCode;

}
