package com.redabeggar.bankAccountApi.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.repository.OperationRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;
import com.redabeggar.bankAccountApi.utils.TransferRequest;


@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

	@Mock
	private OperationRepository operationRepository;
	@Mock
	private AccountService accountService;

	@InjectMocks
	OperationService operationService;
	
	private OperationRequest operationRequest;
	private TransferRequest transferRequest;
	private Account account;
	private Account account2;
	private Operation deposit_operation;
	private Operation withdraw_operation;
	private Operation transfer_operation;

	@Before
	public void setUp() throws Exception {
		operationRequest = new OperationRequest(12345L, 500);
	    transferRequest = new TransferRequest(12345L,6789L,1000 );

		
		// Deposit Operation -balance = 2000-
		account = new Account(12345L, 1500);
		deposit_operation = new Operation(account, 500, OperationType.DEPOSIT);
		account.setBalance(account.getBalance() + deposit_operation.getAmount());
		deposit_operation.setAccount(account);

		// Withdraw Operation -balance = 1000-
		account2 = new Account(6789L, 1500);
		withdraw_operation = new Operation(account2, 500, OperationType.WITHDRAW);
		account2.setBalance(account2.getBalance() - withdraw_operation.getAmount());
		withdraw_operation.setAccount(account2);

		// Transfer Operation -balance1 = 1200- and -balance2 = 1800-
		transfer_operation = new Operation(account, account2,800, OperationType.TRANSFERT);
		account.setBalance(account.getBalance() - transfer_operation.getAmount());
		account2.setBalance(account2.getBalance() + transfer_operation.getAmount());
		transfer_operation.setAccount(account);
		transfer_operation.setPayee(account2);
	}

	

	
	@Test
	public void should_MakeADeposit() throws Exception {
			
		given(accountService.updateAccount_when_deposit(anyObject())).willReturn(account);
		given(operationRepository.save(any(Operation.class))).willReturn(deposit_operation);
		
		
		Operation operation = operationService.makeADeposit(operationRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.DEPOSIT);
	}
	
	
	@Test
	public void should_MakeAWithdraw() throws Exception {
			
		given(accountService.updateAccount_when_withdraw(anyObject())).willReturn(account2);
		given(operationRepository.save(any(Operation.class))).willReturn(withdraw_operation);
		
		
		Operation operation = operationService.makeAWithdraw(operationRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1800);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.WITHDRAW);
	}
	
	@Test
	public void should_MakeATransfer() throws Exception {
			
		given(accountService.updateAccount_when_deposit(anyObject())).willReturn(account);
		given(accountService.updateAccount_when_withdraw(anyObject())).willReturn(account2);
		given(operationRepository.save(any(Operation.class))).willReturn(transfer_operation);
		
		
		Operation operation = operationService.makeATransfer(transferRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getPayee().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(operation.getPayee().getBalance()).isEqualTo(1800);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.TRANSFERT);
	}
}
