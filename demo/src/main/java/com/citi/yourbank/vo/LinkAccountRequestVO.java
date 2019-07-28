package com.citi.yourbank.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("LinkAccountRequest")
@Data
public class LinkAccountRequestVO {

	@NotNull
	private String accountToken;
}
