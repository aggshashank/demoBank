package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("OwnerDetail")
@Data
public class OwnerDetailVO {

	private String userId;
	
	private String firstName;
	
	private String lastName;
}
