package com.citi.yourbank.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("Account")
@Data
public class AccountVO {

	@JsonProperty("accountId")
	private String id;
	
	private String accountToken;
	
	private String displayAccountNumber;
	
	private double balance;
	
	private double limit;
}
