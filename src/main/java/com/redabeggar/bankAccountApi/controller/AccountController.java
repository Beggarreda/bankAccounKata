package com.redabeggar.bankAccountApi.controller;

import com.redabeggar.bankAccountApi.exception.AccountAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.service.IAccountService;


@RestController
public class AccountController {

	
	
	@Autowired
	private IAccountService accountService;


	@PostMapping(path = "/account", consumes = "application/json", produces = "application/json")
	public Account createDeposit(@RequestBody Account account) {

		return accountService.create(account);

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.ALREADY_REPORTED)
	private void AccountAlreadyExistHandler(AccountAlreadyExistException e){

	}
}
