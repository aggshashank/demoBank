package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@ApiModel("CustomerProfile")
@Accessors(chain = true)
@Data    
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileVO {
	
	private String firstName;
	
	private String lastName;
	
	private String userId;
	
}
