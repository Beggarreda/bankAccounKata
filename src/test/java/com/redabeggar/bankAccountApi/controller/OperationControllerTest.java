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
import com.redabeggar.bankAccountApi.service.OperationService;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;

@RunWith(SpringRunner.class)
@WebMvcTest(OperationController.class)
public class OperationControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private OperationService operationService;

	@Autowired
	private ObjectMapper mapper;

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
	public void should_makeADeposit() throws Exception {

		given(operationService.makeADeposit(anyObject())).willReturn(operation);

		mockMvc.perform(post("/deposit").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(operationRequest))).andExpect(status().isOk())
				.andExpect(jsonPath("account.accountNumber").value(12345)).andExpect(jsonPath("amount").value(500))
				.andExpect(jsonPath("account.balance").value(2000));
	}
}
