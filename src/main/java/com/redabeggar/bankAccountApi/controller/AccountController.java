package com.redabeggar.bankAccountApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.service.IAccountService;


@RestController
public class AccountController {

	
	
	@Autowired
	private IAccountService accountService;


	@PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
	public Account createDeposit(@RequestBody Account account) {

		return accountService.createAccount(account);

	}
}
