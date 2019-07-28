package com.citi.yourbank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.yourbank.entity.CustomerProfile;

@Repository
public interface CustomerProfileRepository extends CrudRepository<CustomerProfile, Integer>{
	
	CustomerProfile findByUserId(String userId);

	CustomerProfile save(CustomerProfile customerRequest);

}
