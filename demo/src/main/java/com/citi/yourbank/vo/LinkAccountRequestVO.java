package com.citi.yourbank.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("LinkAccountRequest")
@Data
public class LinkAccountRequestVO {

	private String accountToken;
}
