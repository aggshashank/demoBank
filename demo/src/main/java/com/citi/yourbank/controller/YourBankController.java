package com.citi.yourbank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.yourbank.config.CustomerProfileVO;
import com.citi.yourbank.vo.AccountSummaryVO;
import com.citi.yourbank.vo.LinkAccountRequestVO;
import com.citi.yourbank.vo.LoginRequestVO;
import com.citi.yourbank.vo.OpenAccountRequestVO;
import com.citi.yourbank.vo.RegisterUserRequestVO;
import com.citi.yourbank.vo.TransactionVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/digital/yourbank")
public class YourBankController {

	@ApiOperation(value="login",response=CustomerProfileVO.class)
	@ApiResponses(value={
			@ApiResponse(code=200,message="Successful login",response=CustomerProfileVO.class),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Customer not found")
	})
	@PostMapping("/customer/login")
	public ResponseEntity<CustomerProfileVO> login(@RequestBody LoginRequestVO loginCredential){

		CustomerProfileVO customerProfile = new CustomerProfileVO();
		return ResponseEntity.ok(customerProfile);
	}

	@ApiOperation(value="register user",response=CustomerProfileVO.class)
	@ApiResponses(value={
			@ApiResponse(code=200,message="user registered successfully",response=CustomerProfileVO.class),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	@PostMapping(value = "/customer/register")
	public ResponseEntity<CustomerProfileVO> registerUser(@RequestBody RegisterUserRequestVO request){

		CustomerProfileVO customerProfile = new CustomerProfileVO();
		return ResponseEntity.ok(customerProfile);
	}

	@ApiOperation(value = "Get all linked accounts by userId")
	@GetMapping("/customer/{userId}/accounts")
	public ResponseEntity<List<AccountSummaryVO>> getAllLinkedAccounts(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId)
					throws ResourceNotFoundException {

		List<AccountSummaryVO> accountSummaryList = new ArrayList<>();

		return ResponseEntity.ok().body(accountSummaryList);
	}

	@ApiOperation(value = "Get all linked accounts by userId")
	@GetMapping("/accounts/{accountId}/transactions")
	public ResponseEntity<List<TransactionVO>> getTransactions(
			@ApiParam(value = "accountId", required = true)
			@PathVariable(value = "accountId") String accountId)
					throws ResourceNotFoundException {

		List<TransactionVO> transactions = new ArrayList<>();

		return ResponseEntity.ok().body(transactions);
	}

	@ApiOperation(value = "link account to the user")
	@PutMapping("/customer/{userId}/account")
	public ResponseEntity<AccountSummaryVO> linkAccount(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "linkAccountRequest", required = true)
			@Valid @RequestBody LinkAccountRequestVO employeeDetails) throws ResourceNotFoundException {
		AccountSummaryVO accountSummary = new AccountSummaryVO();

		return ResponseEntity.ok(accountSummary);
	}

	@ApiOperation(value="open account",response=AccountSummaryVO.class)
	@ApiResponses(value={
			@ApiResponse(code=200,message="account opened successfully",response=AccountSummaryVO.class),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	@PostMapping(value = "/customer/{userId}/account")
	public ResponseEntity<AccountSummaryVO> openAccount(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "openAccountRequest", required = true) 
			@RequestBody OpenAccountRequestVO request){

		AccountSummaryVO accountSummary = new AccountSummaryVO();
		return ResponseEntity.ok(accountSummary);
	}


}
