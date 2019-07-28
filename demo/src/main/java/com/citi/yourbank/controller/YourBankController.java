package com.citi.yourbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.yourbank.entity.CustomerProfile;
import com.citi.yourbank.repository.AccountRepository;
import com.citi.yourbank.repository.CustomerAccountRepository;
import com.citi.yourbank.repository.CustomerProfileRepository;
import com.citi.yourbank.vo.AccountSummaryVO;
import com.citi.yourbank.vo.CustomerProfileVO;
import com.citi.yourbank.vo.LinkAccountRequestVO;
import com.citi.yourbank.vo.LoginRequestVO;
import com.citi.yourbank.vo.OpenAccountRequestVO;
import com.citi.yourbank.vo.RegisterUserRequestVO;
import com.citi.yourbank.vo.TransactionVO;
import com.citi.yourbank.vo.TransferFundRequestVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/digital/yourbank")
@Slf4j
public class YourBankController {

	@Autowired
	private CustomerProfileRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerAccountRepository customerAccountRepository;

	@ApiOperation(value="login",response=CustomerProfileVO.class)
	@ApiResponses(value={
			@ApiResponse(code=200,message="Successful login",response=CustomerProfileVO.class),
			@ApiResponse(code=500,message="Internal Server Error"),
			@ApiResponse(code=404,message="Customer not found")
	})
	@PostMapping("/customer/login")
	public ResponseEntity<CustomerProfileVO> login(@RequestBody LoginRequestVO loginCredential){
		log.debug("login called");
		CustomerProfile customerProfile = customerRepository.findByUserId(loginCredential.getUserId());
		CustomerProfileVO customerProfileVO = new CustomerProfileVO();
		BeanUtils.copyProperties(customerProfile, customerProfileVO);
		return ResponseEntity.ok(customerProfileVO);
	}

	@ApiOperation(value="register user",response=CustomerProfileVO.class)
	@ApiResponses(value={
			@ApiResponse(code=200,message="user registered successfully",response=CustomerProfileVO.class),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	@PostMapping(value = "/customer/register")
	public ResponseEntity<CustomerProfileVO> registerUser(@RequestBody RegisterUserRequestVO request){

		CustomerProfile customerRequest = new CustomerProfile();
		customerRequest.setFirstName(request.getFirstName());
		customerRequest.setLastName(request.getLastName());
		customerRequest.setUserId(request.getUserId());
		
		customerRequest = customerRepository.save(customerRequest);
		CustomerProfileVO customerProfileVO = new CustomerProfileVO();
		BeanUtils.copyProperties(customerRequest, customerProfileVO);

		return ResponseEntity.ok(customerProfileVO);
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
	public ResponseEntity<List<AccountSummaryVO>> linkAccount(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "linkAccountRequest", required = true)
			@Valid @RequestBody LinkAccountRequestVO employeeDetails) throws ResourceNotFoundException {

		List<AccountSummaryVO> accountSummaryList = new ArrayList<>();

		return ResponseEntity.ok().body(accountSummaryList);
	}

	@ApiOperation(value="open account")
	@ApiResponses(value={
			@ApiResponse(code=200,message="account opened successfully",response=AccountSummaryVO.class),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	@PostMapping(value = "/customer/{userId}/account")
	public ResponseEntity<List<AccountSummaryVO>> openAccount(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "openAccountRequest", required = true) 
			@RequestBody OpenAccountRequestVO request){

		UUID hash = UUID.fromString(userId);
		
		
		
		
		
		List<AccountSummaryVO> accountSummaryList = new ArrayList<>();

		return ResponseEntity.ok().body(accountSummaryList);
	}

	@ApiResponses(value={
			@ApiResponse(code=200,message="fund transfered successfully"),
			@ApiResponse(code=500,message="Internal Server Error")
	})
	@PostMapping(value = "/customer/{userId}/fund")
	public ResponseEntity<List<TransactionVO>> transferFund(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "transferFundRequest", required = true) 
			@RequestBody TransferFundRequestVO request){

		List<TransactionVO> transactions = new ArrayList<>();

		return ResponseEntity.ok().body(transactions);
	}



}
