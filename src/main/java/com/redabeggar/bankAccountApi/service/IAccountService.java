package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public interface IAccountService {

	Account getByAccountNumber(long accountNumber);
	Account update_when_deposit(OperationRequest operationRequest);
    Account create(Account account);
	Account update_when_withdraw(OperationRequest operationRequest);

}