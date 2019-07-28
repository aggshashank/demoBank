package com.citi.yourbank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.yourbank.entity.BankAccount;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Integer>{
	
	BankAccount findById(int accountId);

	BankAccount findByAccountToken(String accountToken);
	
}
