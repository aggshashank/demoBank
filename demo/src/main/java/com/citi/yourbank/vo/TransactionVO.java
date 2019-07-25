package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("Transaction")
@Data
public class TransactionVO {
	
	private String transactionId;
	
	private String transactionDescription;
	
	private double transactionAmount;
	
	private String transactionType;
}
