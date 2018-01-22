package com.redabeggar.bankAccountApi.utils;

public class OperationRequest {

	private Long accountNumber;
	private double amount;
	public OperationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OperationRequest(Long accountNumber, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
