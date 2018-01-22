package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

public interface IOperationService {

	Operation makeADeposit(OperationRequest operationRequest);

	Operation makeAWithdraw(OperationRequest operationRequest);

	Object makeATransfer(Object anyObject);

}