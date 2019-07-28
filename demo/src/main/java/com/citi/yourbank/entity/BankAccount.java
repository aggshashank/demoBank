package com.citi.yourbank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "BANK_ACCOUNT")
@EqualsAndHashCode
public class BankAccount{

	@Id
	@GeneratedValue
	private int id;
	
	private String accountToken;

	private String accountDesc;
	
	private double balance;
	 
	private double accountLimit;
	
}
