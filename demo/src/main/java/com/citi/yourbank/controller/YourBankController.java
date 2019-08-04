package com.citi.yourbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citi.yourbank.entity.BankAccount;
import com.citi.yourbank.entity.CustomerAccount;
import com.citi.yourbank.entity.CustomerProfile;
import com.citi.yourbank.entity.Transaction;
import com.citi.yourbank.repository.BankAccountRepository;
import com.citi.yourbank.repository.CustomerAccountRepository;
import com.citi.yourbank.repository.CustomerProfileRepository;
import com.citi.yourbank.repository.TransactionRepository;
import com.citi.yourbank.vo.AccountSummaryVO;
import com.citi.yourbank.vo.AccountVO;
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
	private BankAccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	
	
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
		customerRequest.setEmailId(request.getEmailId());
		customerRequest.setPhoneNumber(request.getPhoneNumber());
		
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

		List<AccountSummaryVO> accountSummaryList = getAccountSummaryList(userId);

		return ResponseEntity.ok().body(accountSummaryList);
	}
	

	@ApiOperation(value = "Get all linked accounts by userId")
	@GetMapping("/accounts/{accountId}/transactions")
	public ResponseEntity<List<TransactionVO>> getTransactions(
			@ApiParam(value = "accountId", required = true)
			@PathVariable(value = "accountId") String accountId)
					throws ResourceNotFoundException {

		List<TransactionVO> transactionVOList = getAllTransactions(accountId);
		return ResponseEntity.ok().body(transactionVOList);
	}

	private List<TransactionVO> getAllTransactions(String accountId){
		List<Transaction> transactions = transactionRepository.findByAccountId(Integer.parseInt(accountId));
		
		List<TransactionVO> transactionVOList = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(transactions)){
			for(Transaction transaction: transactions) {
				TransactionVO transactionVO = new TransactionVO();
				BeanUtils.copyProperties(transaction, transactionVO);
				CustomerProfile profile = customerRepository.findByUserId(transaction.getUserId());
				CustomerProfileVO profileVO = new CustomerProfileVO();
				BeanUtils.copyProperties(profile, profileVO);
				transactionVO.setContributor(profileVO);
				transactionVO.setTransactionId(Integer.toString(transaction.getTransactionId()));
				transactionVOList.add(transactionVO);
			}
		}
		
		return transactionVOList;
	}
	
	
	@ApiOperation(value = "link account to the user")
	@PutMapping("/customer/{userId}/account")
	public ResponseEntity<List<AccountSummaryVO>> linkAccount(
			@ApiParam(value = "userId", required = true)
			@PathVariable(value = "userId") String userId,
			@ApiParam(value = "linkAccountRequest", required = true)
			@Valid @RequestBody LinkAccountRequestVO requestVO) throws ResourceNotFoundException {

		
		BankAccount account = accountRepository.findByAccountToken(requestVO.getAccountToken());
		CustomerAccount accountRequest = new CustomerAccount();
		accountRequest.setAccountId(account.getId());
		accountRequest.setUserId(userId);
		accountRequest.setAssociationType("SHARED");
		customerAccountRepository.save(accountRequest);
		
		List<AccountSummaryVO> accountSummaryList = getAccountSummaryList(userId);

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

		UUID hash = UUID.randomUUID();
		
		BankAccount account = new BankAccount();
		account.setAccountToken(hash.toString());
		account.setAccountLimit(request.getLimit());
		account.setAccountDesc(request.getAccountDescription());
		account.setAccountTitle(request.getAccountTitle());
		
		account = accountRepository.save(account);
		
		CustomerAccount customerAccount = new CustomerAccount();
		customerAccount.setAccountId(account.getId());
		customerAccount.setUserId(userId);
		customerAccount.setAssociationType("OWNED");
		customerAccountRepository.save(customerAccount);
		
		List<AccountSummaryVO> accountSummaryList = getAccountSummaryList(userId);

		return ResponseEntity.ok().body(accountSummaryList);
	}
	
	private CustomerProfile getOwnerDetail(int accountId) {
		CustomerProfile customerProfile = null;
		List<CustomerAccount> customerAccountList = customerAccountRepository.findByAccountId(accountId);
		if(!CollectionUtils.isEmpty(customerAccountList)) {
			for(CustomerAccount account: customerAccountList) {
				if(account.getAssociationType().equals("OWNED")) {
					customerProfile = customerRepository.findByUserId(account.getUserId());
					break;
				}
			}
		}
		
		return customerProfile;
	}
	
	private List<AccountSummaryVO> getAccountSummaryList(String userId){
		
		List<AccountSummaryVO> accountSummaryList = new ArrayList<>();
		
		List<CustomerAccount> customerAccountList = customerAccountRepository.findByUserId(userId);
		if(!CollectionUtils.isEmpty(customerAccountList)){
			for(CustomerAccount custAccount: customerAccountList) {
				AccountVO accountVO = new AccountVO();
				BankAccount account = accountRepository.findById(custAccount.getAccountId());
				BeanUtils.copyProperties(account, accountVO);
				accountVO.setId(String.valueOf(account.getId()));
				AccountSummaryVO accountSummaryVO = new AccountSummaryVO();
				accountSummaryVO.setAccount(accountVO);
				accountSummaryVO.setAssociationType(custAccount.getAssociationType());
				if(custAccount.getAssociationType().equals("SHARED")) {
					CustomerProfile ownerProfile = getOwnerDetail(custAccount.getAccountId());
					CustomerProfileVO ownerProfileVO = new CustomerProfileVO();
					BeanUtils.copyProperties(ownerProfile, ownerProfileVO);
					accountSummaryVO.setOwnerDetail(ownerProfileVO);
				}
				accountSummaryVO.setAccountDescription(account.getAccountDesc());
				accountSummaryVO.setAccountTitle(account.getAccountTitle());
				accountSummaryList.add(accountSummaryVO);
			}
		}
		return accountSummaryList;
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
		
		Transaction transaction = new Transaction();
		transaction.setAccountId(Integer.parseInt(request.getAccountId()));
		transaction.setUserId(userId);
		transaction.setTransactionAmount(request.getAmount());
		transaction.setTransactionDescription("Contribution");
		
		transactionRepository.save(transaction);
		
		BankAccount account = accountRepository.findById(Integer.parseInt(request.getAccountId()));
		account.setBalance(account.getBalance() + request.getAmount());
		accountRepository.save(account);

		List<TransactionVO> transactions = getAllTransactions(request.getAccountId());

		return ResponseEntity.ok().body(transactions);
	}



}
