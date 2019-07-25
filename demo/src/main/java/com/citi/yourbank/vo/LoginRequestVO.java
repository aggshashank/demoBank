package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("LoginRequest")
@Data
public class LoginRequestVO {
	
	private String userId;
	
	private String password;

}
