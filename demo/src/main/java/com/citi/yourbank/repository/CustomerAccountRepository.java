package com.citi.yourbank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.yourbank.entity.CustomerAccount;

@Repository
public interface CustomerAccountRepository extends CrudRepository<CustomerAccount, Integer>{
	
	List<CustomerAccount> findByAccountId(int accountId);
	
	List<CustomerAccount> findByUserId(String userId);

	CustomerAccount save(CustomerAccount accountRequest);

}
