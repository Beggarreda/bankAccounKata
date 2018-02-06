package com.redabeggar.bankAccountApi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.redabeggar.bankAccountApi.utils.OperationType;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Operation implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
    @JoinColumn(name = "account_id")
	private Account account;
	private double amount;
	@CreationTimestamp
	private Date creationDate;
	@UpdateTimestamp
	private Date updatDate;
	private OperationType operationType;
	@ManyToOne
    @JoinColumn(name = "payee_account_id")
	private Account payee;
	



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


	
	
	
}
