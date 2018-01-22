package com.redabeggar.bankAccountApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.repository.OperationRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;
import com.redabeggar.bankAccountApi.utils.TransferRequest;

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
	public Operation makeATransfer(TransferRequest transferRequest) {
		OperationRequest payeeRequest = new OperationRequest(transferRequest.getPayeeAccountNumber(), transferRequest.getAmount());;
		Account account = accountService.updateAccount_when_withdraw(transferRequest);
		Account account2 = accountService.updateAccount_when_deposit(payeeRequest);
		Operation operation = new Operation(account, account2,transferRequest.getAmount(), OperationType.TRANSFERT);
		operationRepository.save(operation);
		
		return operation;
	}


	@Override
	public List<Operation> getTransferHistory(long accountNumber) {
		List<Operation> transfers = new ArrayList<Operation>();
		transfers.addAll(operationRepository.findByAccountAccountNumber(accountNumber));
		transfers.addAll(operationRepository.findByPayeeAccountNumber(accountNumber));
		transfers= transfers.stream().filter(operation -> operation.getOperationType().equals(OperationType.TRANSFERT)).collect(Collectors.toList());
		return transfers;
	}

}
