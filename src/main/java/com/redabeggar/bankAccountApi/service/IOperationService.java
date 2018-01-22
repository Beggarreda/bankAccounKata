package com.redabeggar.bankAccountApi.service;

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.TransferRequest;

public interface IOperationService {

	Operation makeADeposit(OperationRequest operationRequest);

	Operation makeAWithdraw(OperationRequest operationRequest);

	Operation makeATransfer(TransferRequest transferRequest);

}