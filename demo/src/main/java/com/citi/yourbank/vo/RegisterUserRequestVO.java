package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("RegisterUserRequest")
@Data
public class RegisterUserRequestVO {
	
	private String userId;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNumber;
	
}
