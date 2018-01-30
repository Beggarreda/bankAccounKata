package com.redabeggar.bankAccountApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
import com.redabeggar.bankAccountApi.exception.AmountNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.repository.OperationRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;
import com.redabeggar.bankAccountApi.utils.TransferRequest;

@Service
@Slf4j
public class OperationService implements IOperationService {

	@Autowired
	IAccountService accountService;
	@Autowired
	OperationRepository operationRepository;
	

	@Override
	public Operation deposit(OperationRequest operationRequest) {

        Account account = accountService.getByAccountNumber(operationRequest.getAccountNumber());
		if(operationRequest.getAmount() <= 0)
			throw new AmountNotValidException("Not Valid Amount ");

		account.setBalance(account.getBalance() + operationRequest.getAmount());
		account = accountService.update(account);
		Operation operation = new Operation(account, operationRequest.getAmount(), OperationType.DEPOSIT);
		operationRepository.save(operation);
		
		return operation;
	}


	@Override
	public Operation withdraw(OperationRequest operationRequest) {
		Account account = accountService.getByAccountNumber(operationRequest.getAccountNumber());
		if(account == null)
			throw new AccountNotFoundException("Account Not Found");
		else if(operationRequest.getAmount() > account.getBalance() )
			throw new AmountNotValidException("Not Valid Amount ");

		account.setBalance(account.getBalance() - operationRequest.getAmount());
		account = accountService.update(account);
		Operation operation = new Operation(account, operationRequest.getAmount(), OperationType.WITHDRAW);
		operationRepository.save(operation);
		
		return operation;
	}


	@Override
	public Operation transfer(TransferRequest transferRequest) {

		OperationRequest payeeRequest = new OperationRequest(transferRequest.getPayeeAccountNumber(), transferRequest.getAmount());
		Account account = accountService.getByAccountNumber(transferRequest.getAccountNumber());
		if(transferRequest.getAmount() > account.getBalance() || transferRequest.getAmount() <= 0)
			throw new AmountNotValidException("Transfer Amount Not Valid ");
		account.setBalance(account.getBalance() - transferRequest.getAmount());


		Account account2 = accountService.getByAccountNumber(payeeRequest.getAccountNumber());
		account2.setBalance(account2.getBalance() + transferRequest.getAmount());

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
