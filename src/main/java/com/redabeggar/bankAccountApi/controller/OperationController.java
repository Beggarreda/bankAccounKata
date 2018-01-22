package com.redabeggar.bankAccountApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.service.OperationService;
import com.redabeggar.bankAccountApi.utils.OperationRequest;


@RestController
public class OperationController {

	
	
	@Autowired
	private OperationService operationService;


	@PostMapping(path = "/deposit", consumes = "application/json", produces = "application/json")
	public Operation createDeposit(@RequestBody OperationRequest operationRequest) {

		return operationService.makeADeposit(operationRequest);

	}
}
