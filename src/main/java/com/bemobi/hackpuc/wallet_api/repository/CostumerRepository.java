package com.bemobi.hackpuc.wallet_api.repository;

import com.bemobi.hackpuc.wallet_api.domain.Costumer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hcfonseca on 7/21/17.
 */
public interface CostumerRepository extends CrudRepository<Costumer, Long> {

    Costumer findByFingerId(Integer fingerId);

}
