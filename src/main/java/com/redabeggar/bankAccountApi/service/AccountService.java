package com.redabeggar.bankAccountApi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.repository.AccountRepository;

public class AccountService {

@Autowired	
private AccountRepository accountRepository;

	public Account getAccount(long accountNumber) {

		return accountRepository.findOne(accountNumber);
	}

}
