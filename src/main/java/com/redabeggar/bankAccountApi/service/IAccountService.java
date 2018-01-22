package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public interface IAccountService {

	Account getAccount(long accountNumber);
	Account updateAccount_when_deposit(OperationRequest operationRequest);
    Account createAccount(Account account);
	Account updateAccount_when_withdraw(OperationRequest operationRequest);

}