package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("AccountSummary")
@Data
public class AccountSummaryVO {
	
	private AccountVO account;
	
	private String associationType;
	
	private CustomerProfileVO ownerDetail;
	
	private String accountDescription;

}
