package com.bemobi.hackpuc.wallet_api.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by hcfonseca on 7/21/17.
 */
@Entity
@Table(name="foo")
public class Foo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String bar;

}
