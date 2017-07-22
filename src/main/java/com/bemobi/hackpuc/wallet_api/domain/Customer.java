package com.bemobi.hackpuc.wallet_api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hcfonseca on 7/21/17.
 */

@Entity
@Data
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "id_finger")
    private Integer fingerId;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CreditCard> creditCards;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fingerId=" + fingerId +
                '}';
    }
}
