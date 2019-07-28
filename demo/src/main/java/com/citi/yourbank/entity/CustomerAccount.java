package com.citi.yourbank.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class CustomerAccount {
	
	@Id
	@GeneratedValue
	private int id;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id") private CustomerProfile customerProfile;
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "id") private Account account;
	 */
	private String userId;
	
	private int accountId;
	
	
	private String associationType;
}
