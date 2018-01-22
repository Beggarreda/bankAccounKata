package com.redabeggar.bankAccountApi.model;

import java.util.Date;

import com.redabeggar.bankAccountApi.utils.OperationType;

public class Operation {
	
	
	private Long id;
	private Account account;
	private double amount;
	private Date creationDate;
	private Date updatDate;
	private OperationType operationType;
	
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	public Operation(Account account, double amount, OperationType operationType) {
		super();
		this.account = account;
		this.amount = amount;
		this.operationType = operationType;
	}




	public Long getId() {
		return id;
	}




	public Account getAccount() {
		return account;
	}




	public double getAmount() {
		return amount;
	}




	public Date getCreationDate() {
		return creationDate;
	}




	public Date getUpdatDate() {
		return updatDate;
	}




	public OperationType getOperationType() {
		return operationType;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public void setAccount(Account account) {
		this.account = account;
	}




	public void setAmount(double amount) {
		this.amount = amount;
	}




	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}




	public void setUpdatDate(Date updatDate) {
		this.updatDate = updatDate;
	}




	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}




	
	
	
	
}
