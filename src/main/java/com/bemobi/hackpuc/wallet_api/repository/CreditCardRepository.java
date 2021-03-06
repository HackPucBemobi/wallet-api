package com.bemobi.hackpuc.wallet_api.repository;

import com.bemobi.hackpuc.wallet_api.domain.CreditCard;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hcfonseca on 7/21/17.
 */
public interface CreditCardRepository extends CrudRepository<CreditCard, Long> {

    CreditCard findById(Long id);

}
