package com.citi.yourbank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transaction {
	
	@Id
	@GeneratedValue
	private int transactionId;
	
	private String transactionDescription;
	
	private double transactionAmount;
	
	private String transactionType;
	
	private int accountId;
	
	private String userId;
}
