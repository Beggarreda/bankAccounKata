package com.redabeggar.bankAccountApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redabeggar.bankAccountApi.exception.AccountAlreadyExistException;
import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
import com.redabeggar.bankAccountApi.exception.AmountNotValidException;
import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.repository.AccountRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

@Service
public class AccountService implements IAccountService {

@Autowired	
private AccountRepository accountRepository;

	
	@Override
	public Account getByAccountNumber(long accountNumber) {
		Account account = accountRepository.findOne(accountNumber);
		if(account == null)
			throw new AccountNotFoundException("Account Not Found");
		return account;
	}

	
	@Override
	public Account update_when_deposit(OperationRequest operationRequest) {
		Account account = accountRepository.findOne(operationRequest.getAccountNumber());
		 if(account == null)
	            throw new AccountNotFoundException("Account Not Found");
		 else if(operationRequest.getAmount() <= 0)
			 throw new AmountNotValidException("Not Valid Amount ");

		 account.setBalance(account.getBalance() + operationRequest.getAmount());

	        return accountRepository.save(account);
	    }


	@Override
	public Account create(Account account) {
		
		 if(accountRepository.findOne(account.getAccountNumber()) != null)
	            throw new AccountAlreadyExistException("Account Already Exist");
		
	        return accountRepository.save(account);
	    }


	@Override
	public Account update_when_withdraw(OperationRequest operationRequest) {
		Account account = accountRepository.findOne(operationRequest.getAccountNumber());
		 if(account == null)
	            throw new AccountNotFoundException("Account Not Found");
		 else if(operationRequest.getAmount() > account.getBalance() )
			 throw new AmountNotValidException("Not Valid Amount ");
		 
		 account.setBalance(account.getBalance() - operationRequest.getAmount());

	        return accountRepository.save(account);
	    }

}
