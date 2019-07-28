package com.citi.yourbank.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferFundRequestVO {
	
	@NotNull
	private String accountId;
	
	@NotNull
	private double amount;
}
