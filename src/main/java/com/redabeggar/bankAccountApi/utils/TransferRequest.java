package com.redabeggar.bankAccountApi.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransferRequest extends OperationRequest{

	private Long payeeAccountNumber ;



	public TransferRequest(Long accountNumber, double amount) {
		super(accountNumber, amount);
		// TODO Auto-generated constructor stub
	}

	public TransferRequest(Long accountNumber,Long payeeAccountNumber, double amount) {
		super(accountNumber, amount);
		this.payeeAccountNumber = payeeAccountNumber;
	}


	
}
	