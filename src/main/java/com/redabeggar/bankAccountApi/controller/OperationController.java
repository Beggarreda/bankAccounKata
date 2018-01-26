package com.redabeggar.bankAccountApi.controller;

import java.util.List;

import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
import com.redabeggar.bankAccountApi.exception.AmountNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.service.IOperationService;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.TransferRequest;


@RestController
public class OperationController {

	
	
	@Autowired
	private IOperationService operationService;


	@PostMapping(path = "/deposit", consumes = "application/json", produces = "application/json")
	public Operation createDeposit(@RequestBody OperationRequest operationRequest) {

		return operationService.deposit(operationRequest);

	}
	
	@PostMapping(path = "/withdraw", consumes = "application/json", produces = "application/json")
	public Operation createWithdraw(@RequestBody OperationRequest operationRequest) {

		return operationService.withdraw(operationRequest);

	}

	@PostMapping(path = "/transfer", consumes = "application/json", produces = "application/json")
	public Operation createTransfer(@RequestBody TransferRequest transferRequest) {

		return operationService.transfer(transferRequest);

	}
	
	@GetMapping("/transfer_history/{accountNumber}")
	private List<Operation> getTransferHistory(@PathVariable long accountNumber) {

		return operationService.getTransferHistory(accountNumber);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void AccountNotFoundHandler(AccountNotFoundException e){

	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	private void AmountMinMaxValueHandler(AmountNotValidException e){

	}
}
