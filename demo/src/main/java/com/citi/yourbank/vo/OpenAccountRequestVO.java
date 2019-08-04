package com.citi.yourbank.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("OpenAccountRequest")
@Data
public class OpenAccountRequestVO {

	@NotNull
	private double limit;
	
	@NotNull
	private String accountDescription;
	
	private String accountTitle;
}
