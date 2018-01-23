package com.redabeggar.bankAccountApi.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyObject;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.redabeggar.bankAccountApi.exception.AccountAlreadyExistException;
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
import com.redabeggar.bankAccountApi.service.IAccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	private IAccountService accountService;

	@Autowired
	private ObjectMapper mapper;

	private Account account;

	@Before
	public void setUp() throws Exception {
		account = new Account(12345L, 1500);

	}

	@Test
	public void should_createAccount() throws Exception {

		given(accountService.createAccount(anyObject())).willReturn(account);
		
		mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(account))).andExpect(status().isOk())
				.andExpect(jsonPath("accountNumber").value(12345))
				.andExpect(jsonPath("balance").value(1500));
	}

	@Test
	public void createAccount_should_ReturnAccountAlreadyExistException() throws Exception {
		String json = mapper.writeValueAsString(account);
		given(accountService.createAccount(anyObject())).willThrow(new AccountAlreadyExistException("Account already exist"));

		mockMvc.perform(post("/account").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(account)))
				.andExpect(status().isAlreadyReported());
	}

}
