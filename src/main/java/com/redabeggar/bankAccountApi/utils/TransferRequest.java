package com.redabeggar.bankAccountApi.utils;

public class TransferRequest extends OperationRequest{

	private Long payeeAccountNumber ;



	public TransferRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransferRequest(Long accountNumber, double amount) {
		super(accountNumber, amount);
		// TODO Auto-generated constructor stub
	}

	public TransferRequest(Long accountNumber,Long payeeAccountNumber, double amount) {
		super(accountNumber, amount);
		this.payeeAccountNumber = payeeAccountNumber;
	}

	public Long getPayeeAccountNumber() {
		return payeeAccountNumber;
	}

	public void setPayeeAccountNumber(Long payeeAccountNumber) {
		this.payeeAccountNumber = payeeAccountNumber;
	}

}
	