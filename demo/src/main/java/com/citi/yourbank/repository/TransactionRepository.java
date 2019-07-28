package com.citi.yourbank.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.citi.yourbank.entity.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer>{
	
	Transaction save(Transaction transaction);
	List<Transaction> findByAccountId(int accountId);
}
