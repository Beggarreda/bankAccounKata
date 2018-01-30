package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public interface IAccountService {

	Account getByAccountNumber(long accountNumber);
	Account update(Account account);
    Account create(Account account);

}