package com.citi.yourbank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.yourbank.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	Account findById(String accountId);

	Account save(Account accountRequest);

}
