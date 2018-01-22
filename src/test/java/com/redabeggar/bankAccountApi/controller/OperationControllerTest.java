package com.redabeggar.bankAccountApi.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.service.IOperationService;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;

@RunWith(SpringRunner.class)
@WebMvcTest(OperationController.class)
public class OperationControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private IOperationService operationService;

	@Autowired
	private ObjectMapper mapper;

	private OperationRequest operationRequest;
	private Account account;
	private Account account2;
	private Operation deposit_operation;
	private Operation withdraw_operation;
	private Operation transfer_operation;

	@Before
	public void setUp() throws Exception {
		operationRequest = new OperationRequest(12345L, 500);

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

		// Transfer Operation 
		transfer_operation = new Operation(account, account2,800, OperationType.TRANSFERT);
		account.setBalance(account.getBalance() - transfer_operation.getAmount());
		account2.setBalance(account2.getBalance() + transfer_operation.getAmount());
		transfer_operation.setAccount(account);
		transfer_operation.setPayee(account2);
	}

	@Test
	public void should_makeADeposit() throws Exception {

		given(operationService.makeADeposit(anyObject())).willReturn(deposit_operation);

		mockMvc.perform(post("/deposit").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(operationRequest))).andExpect(status().isOk())
				.andExpect(jsonPath("account.accountNumber").value(12345)).andExpect(jsonPath("amount").value(500))
				.andExpect(jsonPath("account.balance").value(2000));
	}

	@Test
	public void should_makeAWithdraw() throws Exception {

		given(operationService.makeAWithdraw(anyObject())).willReturn(withdraw_operation);

		mockMvc.perform(post("/withdraw").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(operationRequest))).andExpect(status().isOk())
				.andExpect(jsonPath("account.accountNumber").value(6789))
				.andExpect(jsonPath("amount").value(500))
				.andExpect(jsonPath("account.balance").value(1000));
	}

	@Test
	public void should_makeATransfer() throws Exception {

		given(operationService.makeATransfer(anyObject())).willReturn(transfer_operation);

		mockMvc.perform(post("/transfer").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(operationRequest))).andExpect(status().isOk())
				.andExpect(jsonPath("account.accountNumber").value(12345))
				.andExpect(jsonPath("payee.accountNumber").value(6789))
				.andExpect(jsonPath("amount").value(800))
				.andExpect(jsonPath("account.balance").value(1200))
				.andExpect(jsonPath("payee.balance").value(1800));
	}
}
