package com.redabeggar.bankAccountApi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
import com.redabeggar.bankAccountApi.exception.AmountNotValidException;
import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.repository.AccountRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public class AccountService {

@Autowired	
private AccountRepository accountRepository;

	public Account getAccount(long accountNumber) {

		return accountRepository.findOne(accountNumber);
	}

	public Account updateAccount(OperationRequest operationRequest) {
		Account account = accountRepository.findOne(operationRequest.getAccountNumber());
		 if(account == null)
	            throw new AccountNotFoundException("Account Not Found");
		 else if(operationRequest.getAmount() <= 0)
			 throw new AmountNotValidException("Not Valid Amount ");
		 
		 account.setBalance(account.getBalance() + operationRequest.getAmount());

	        return accountRepository.save(account);
	    }

}
