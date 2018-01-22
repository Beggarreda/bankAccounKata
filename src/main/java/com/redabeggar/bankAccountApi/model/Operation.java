package com.redabeggar.bankAccountApi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.redabeggar.bankAccountApi.utils.OperationType;

@Entity
public class Operation implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "account_id")
	private Account account;
	private double amount;
	@CreatedDate
	private Date creationDate;
	@LastModifiedDate
	private Date updatDate;
	private OperationType operationType;
	@ManyToOne
    @JoinColumn(name = "payee_account_id")
	private Account payee;
	
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



	public Operation(Account account, Account payee , double amount, OperationType operationType) {
		super();
		this.account = account;
		this.amount = amount;
		this.operationType = operationType;
		this.payee = payee;
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




	public Account getPayee() {
		return payee;
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




	public void setPayee(Account payee) {
		this.payee = payee;
	}




	
	
	
	
}
