package com.redabeggar.bankAccountApi.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;

import java.util.ArrayList;
import java.util.List;

import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
import com.redabeggar.bankAccountApi.exception.AmountNotValidException;
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
	private Operation transfer_operation2;
	private Operation transfer_operation3;
	private Operation transfer_operation4;
	private List<Operation> list_transfer_operation;
	private List<Operation> list_transfer_operation2;

	@Before
	public void setUp() throws Exception {
		operationRequest = new OperationRequest(12345L, 500);
		transferRequest = new TransferRequest(12345L, 6789L, 800);

		// Deposit Operation -balance = 2000-
		account = new Account(12345L, 1500);
		deposit_operation = new Operation(account, operationRequest.getAmount(), OperationType.DEPOSIT);
		account.setBalance(account.getBalance() + deposit_operation.getAmount());
		deposit_operation.setAccount(account);

		// Withdraw Operation -balance = 1000-
		account2 = new Account(6789L, 1500);
		withdraw_operation = new Operation(account2, operationRequest.getAmount(), OperationType.WITHDRAW);
		account2.setBalance(account2.getBalance() - withdraw_operation.getAmount());
		withdraw_operation.setAccount(account2);

		// Transfer Operation -balance1 = 1200- and -balance2 = 1800-
		transfer_operation = new Operation(account, account2, transferRequest.getAmount(), OperationType.TRANSFERT);
		account.setBalance(account.getBalance() - transfer_operation.getAmount());
		account2.setBalance(account2.getBalance() + transfer_operation.getAmount());
		transfer_operation.setAccount(account);
		transfer_operation.setPayee(account2);

		// transfer operations
		transfer_operation2 = new Operation(account, account2, 500, OperationType.TRANSFERT);
		transfer_operation3 = new Operation(account, account2, 900, OperationType.TRANSFERT);
		transfer_operation4 = new Operation(account2, account, 300, OperationType.TRANSFERT);
		list_transfer_operation = new ArrayList<Operation>();
		list_transfer_operation2 = new ArrayList<Operation>();
		list_transfer_operation.add(transfer_operation2);
		list_transfer_operation.add(transfer_operation3);
		list_transfer_operation2.add(transfer_operation4);
	}

	@Test
	public void Should_Make_A_Deposit() throws Exception {

		given(accountService.update_when_deposit(anyObject())).willReturn(account);
		given(operationRepository.save(any(Operation.class))).willReturn(deposit_operation);

		Operation operation = operationService.deposit(operationRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.DEPOSIT);
	}

	@Test
	public void Should_Make_A_Withdraw() throws Exception {

		given(accountService.update_when_withdraw(anyObject())).willReturn(account2);
		given(operationRepository.save(any(Operation.class))).willReturn(withdraw_operation);

		Operation operation = operationService.withdraw(operationRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1800);
		Assertions.assertThat(operation.getAmount()).isEqualTo(500);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.WITHDRAW);
	}

	@Test
	public void Should_Make_A_Transfer() throws Exception {

		given(accountService.update_when_withdraw(anyObject())).willReturn(account);
		given(accountService.update_when_deposit(anyObject())).willReturn(account2);
		given(operationRepository.save(any(Operation.class))).willReturn(transfer_operation);

		Operation operation = operationService.transfer(transferRequest);

		Assertions.assertThat(operation.getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operation.getPayee().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(operation.getAmount()).isEqualTo(800);
		Assertions.assertThat(operation.getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(operation.getPayee().getBalance()).isEqualTo(1800);
		Assertions.assertThat(operation.getOperationType()).isEqualTo(OperationType.TRANSFERT);
	}

	@Test
	public void Should_Get_Transfer_History() throws Exception {

		given(operationRepository.findByAccountAccountNumber(anyLong())).willReturn(list_transfer_operation);
		given(operationRepository.findByPayeeAccountNumber(anyLong())).willReturn(list_transfer_operation2);

		List<Operation> operations = operationService.getTransferHistory(12345L);

		Assertions.assertThat(operations.size()).isEqualTo(3);
		Assertions.assertThat(operations.get(0).getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(operations.get(1).getPayee().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(operations.get(0).getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(operations.get(1).getAmount()).isEqualTo(900);
		Assertions.assertThat(operations.get(2).getOperationType()).isEqualTo(OperationType.TRANSFERT);
	}

	@Test(expected = AccountNotFoundException.class)
	public void Should_Throw_Account_Not_Found_Exception_When_Making_Deposit() throws Exception {
		given(accountService.update_when_deposit(anyObject())).willThrow(new AccountNotFoundException("Error making a Deposit : Account Not Found"));

		Operation operation = operationService.deposit(operationRequest);

	}

	@Test(expected = AmountNotValidException.class)
	public void Should_Throw_Amount_Not_Valid_Exception_When_Making_Deposit() throws Exception {
		given(accountService.update_when_deposit(anyObject())).willThrow(new AmountNotValidException("Error making a Deposit : Amount Not Valid"));

		Operation operation = operationService.deposit(operationRequest);

	}
}
