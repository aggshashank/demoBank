package com.citi.yourbank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Account {

	@Id
	@GeneratedValue
	private String id;
	
	private String accountToken;
	
	private String displayAccountNumber;
	
	private double balance;
	
	private double limit;
}
