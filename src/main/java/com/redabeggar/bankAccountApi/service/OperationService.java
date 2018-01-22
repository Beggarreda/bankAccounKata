package com.redabeggar.bankAccountApi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.repository.OperationRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;

@Service
public class OperationService implements IOperationService {

	@Autowired
	IAccountService accountService;
	@Autowired
	OperationRepository operationRepository;
	

	@Override
	public Operation makeADeposit(OperationRequest operationRequest) {
		
		
		Account account = accountService.updateAccount_when_deposit(operationRequest);
		Operation operation = new Operation(account, operationRequest.getAmount(), OperationType.DEPOSIT);
		operationRepository.save(operation);
		
		return operation;
	}


	@Override
	public Operation makeAWithdraw(OperationRequest operationRequest) {
		Account account = accountService.updateAccount_when_withdraw(operationRequest);
		Operation operation = new Operation(account, operationRequest.getAmount(), OperationType.WITHDRAW);
		operationRepository.save(operation);
		
		return operation;
	}


	@Override
	public Object makeATransfer(Object anyObject) {
		// TODO Auto-generated method stub
		return null;
	}

}
