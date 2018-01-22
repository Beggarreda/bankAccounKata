package com.redabeggar.bankAccountApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

		return operationService.makeADeposit(operationRequest);

	}
	
	@PostMapping(path = "/withdraw", consumes = "application/json", produces = "application/json")
	public Operation createWithdraw(@RequestBody OperationRequest operationRequest) {

		return operationService.makeAWithdraw(operationRequest);

	}
	
	@PostMapping(path = "/transfer", consumes = "application/json", produces = "application/json")
	public Operation createTransfer(@RequestBody TransferRequest transferRequest) {

		return operationService.makeATransfer(transferRequest);

	}
}
