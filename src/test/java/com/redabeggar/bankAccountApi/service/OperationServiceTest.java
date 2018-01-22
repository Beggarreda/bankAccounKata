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


@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

	@Mock
	private OperationRepository operationRepository;
	@Mock
	private AccountService accountService;

	@InjectMocks
	OperationService operationService;
	
	private OperationRequest operationRequest;
	private Account account;
	private Operation operation;

	@Before
	public void setUp() throws Exception {
		operationRequest = new OperationRequest(12345L, 500);
		account = new Account(12345L, 1500);
		operation = new Operation(account, 500, OperationType.DEPOSIT);
		account.setBalance(account.getBalance() + operation.getAmount());
		operation.setAccount(account);
	}

	

	
	@Test
	public void should_MakeADeposit() throws Exception {
			
		given(accountService.updateAccount(anyObject())).willReturn(account);
		given(operationRepository.save(any(Operation.class))).willReturn(operation);
		
		
		Operation operation = operationService.makeADeposit(operationRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(2000);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.DEPOSIT);
	}
}
