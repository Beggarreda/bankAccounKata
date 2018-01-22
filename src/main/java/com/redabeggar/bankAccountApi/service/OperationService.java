package com.redabeggar.bankAccountApi.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.repository.OperationRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;

public class OperationService {

	@Autowired
	AccountService accountService;
	@Autowired
	OperationRepository operationRepository;
	
	public Operation makeADeposit(OperationRequest operationRequest) {
		
		
		Account account = accountService.updateAccount(operationRequest);
		Operation operation = new Operation(account, operationRequest.getAmount(), OperationType.DEPOSIT);
		operationRepository.save(operation);
		
		return operation;
	}

}
