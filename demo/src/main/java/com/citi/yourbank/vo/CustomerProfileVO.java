package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@ApiModel("CustomerProfile")
@Data
public class CustomerProfileVO {
	
	private String firstName;
	
	private String lastName;
	
	private String userId;
	
}
