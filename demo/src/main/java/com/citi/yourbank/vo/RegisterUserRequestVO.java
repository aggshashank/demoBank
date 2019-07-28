package com.citi.yourbank.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RegisterUserRequest")
@Data
public class RegisterUserRequestVO {
	
	@NotNull
	private String userId;
	
	private String password;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	private String phoneNumber;
	
}
