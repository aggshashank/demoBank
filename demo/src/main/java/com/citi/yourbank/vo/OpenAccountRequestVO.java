package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("OpenAccountRequest")
@Data
public class OpenAccountRequestVO {

	private double limit;
	
	private String accountDescription;
}
