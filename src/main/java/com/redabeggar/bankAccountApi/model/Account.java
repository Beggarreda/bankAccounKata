package com.redabeggar.bankAccountApi.model;

import java.util.Date;

public class Account {
	private Long accountNumber;
	private double balance;
	private Date creationDate;
	private Date updateDate;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Account(Long accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}


	public Long getAccountNumber() {
		return accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	

}
