package com.redabeggar.bankAccountApi.service;

import java.util.List;

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.TransferRequest;

public interface IOperationService {

	Operation deposit(OperationRequest operationRequest);

	Operation withdraw(OperationRequest operationRequest);

	Operation transfer(TransferRequest transferRequest);

	List<Operation> getTransferHistory(long anyLong);

}