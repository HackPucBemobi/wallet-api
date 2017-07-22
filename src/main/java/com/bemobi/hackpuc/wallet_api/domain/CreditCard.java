package com.bemobi.hackpuc.wallet_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by hcfonseca on 7/21/17.
 */

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_card_id_seq")
    @SequenceGenerator(name="credit_card_id_seq", sequenceName="credit_card_id_seq", allocationSize=1)
    private Long id;

    @Column(name = "card_token")
    private String cardToken;

    private String brand;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "security_code")
    private String securityCode;

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardToken='" + cardToken + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
