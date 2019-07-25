package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("Account")
@Data
public class AccountVO {

	private String accountId;
	
	private String accountToken;
	
	private String displayAccountNumber;
	
	private double balance;
	
	private double limit;
}
