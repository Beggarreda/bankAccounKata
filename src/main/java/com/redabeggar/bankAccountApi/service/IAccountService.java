package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public interface IAccountService {

	Account getAccount(long accountNumber);

	Account updateAccount(OperationRequest operationRequest);

}