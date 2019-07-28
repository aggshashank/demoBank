package com.citi.yourbank.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("LoginRequest")
@Data
public class LoginRequestVO {
	
	@NotNull
	private String userId;
	
	private String password;

}
