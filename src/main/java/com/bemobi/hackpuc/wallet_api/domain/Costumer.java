package com.bemobi.hackpuc.wallet_api.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by hcfonseca on 7/21/17.
 */

@Entity
@Data
@Table(name="costumer")
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "id_finger")
    private Integer fingerId;

    @OneToMany(mappedBy = "costumer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CreditCard> creditCards;

    @Override
    public String toString() {
        return "Costumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fingerId=" + fingerId +
                '}';
    }
}
