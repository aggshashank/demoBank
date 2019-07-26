package com.citi.yourbank.vo;

import lombok.Data;

@Data
public class TransferFundRequestVO {
	private String accountId;
	
	private double amount;
}
