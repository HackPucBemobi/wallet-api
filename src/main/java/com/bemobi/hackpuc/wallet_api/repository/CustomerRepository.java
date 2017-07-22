package com.bemobi.hackpuc.wallet_api.repository;

import com.bemobi.hackpuc.wallet_api.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by hcfonseca on 7/21/17.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByFingerId(Integer fingerId);

}
