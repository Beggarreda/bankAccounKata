package com.redabeggar.bankAccountApi;

import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;
import com.redabeggar.bankAccountApi.utils.TransferRequest;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountApiIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	@Test
	public void should_CreateAnAccount() throws Exception {

		// arrange
		Account account = new Account(12345L, 1500);

		// act
		HttpEntity<Account> request = new HttpEntity<Account>(account);
		ResponseEntity<Account> response = restTemplate.exchange("/account", HttpMethod.POST, request, Account.class);

		// assert

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(response.getBody().getBalance()).isEqualTo(1500);

	}
	
	@Test
	public void should_CreateAnOtherAccount() throws Exception {

		// arrange
		Account account = new Account(6789L, 1600);

		// act
		HttpEntity<Account> request = new HttpEntity<Account>(account);
		ResponseEntity<Account> response = restTemplate.exchange("/account", HttpMethod.POST, request, Account.class);

		// assert

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(response.getBody().getBalance()).isEqualTo(1600);

	}

	@Test
	public void should_MakeADeposit() throws Exception {

		// arrange
		OperationRequest operationRequest = new OperationRequest(12345L, 500);

		// act
		HttpEntity<OperationRequest> request = new HttpEntity<OperationRequest>(operationRequest);
		ResponseEntity<Operation> response = restTemplate.exchange("/deposit", HttpMethod.POST, request, Operation.class);

		// assert

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(response.getBody().getAmount()).isEqualTo(500);
		Assertions.assertThat(response.getBody().getAccount().getBalance()).isEqualTo(2000);
		Assertions.assertThat(response.getBody().getOperationType()).isEqualTo(OperationType.DEPOSIT);

	}
	
	@Test
	public void should_MakeAWithdraw() throws Exception {

		// arrange
		OperationRequest operationRequest = new OperationRequest(6789L, 1000);

		// act
		HttpEntity<OperationRequest> request = new HttpEntity<OperationRequest>(operationRequest);
		ResponseEntity<Operation> response = restTemplate.exchange("/withdraw", HttpMethod.POST, request, Operation.class);

		// assert

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getAccount().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(response.getBody().getAmount()).isEqualTo(1000);
		Assertions.assertThat(response.getBody().getAccount().getBalance()).isEqualTo(1400);
		Assertions.assertThat(response.getBody().getOperationType()).isEqualTo(OperationType.WITHDRAW);

	}
	
	@Test
	public void should_MakeATransfer() throws Exception {

		// arrange
		TransferRequest transferRequest = new TransferRequest(12345L,6789L,800 );

		// act
		HttpEntity<TransferRequest> request = new HttpEntity<TransferRequest>(transferRequest);
		ResponseEntity<Operation> response = restTemplate.exchange("/transfer", HttpMethod.POST, request, Operation.class);

		// assert

		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getAccount().getAccountNumber()).isEqualTo(12345);
		Assertions.assertThat(response.getBody().getPayee().getAccountNumber()).isEqualTo(6789);
		Assertions.assertThat(response.getBody().getAmount()).isEqualTo(800);
		Assertions.assertThat(response.getBody().getAccount().getBalance()).isEqualTo(1200);
		Assertions.assertThat(response.getBody().getPayee().getBalance()).isEqualTo(2400);
		Assertions.assertThat(response.getBody().getOperationType()).isEqualTo(OperationType.TRANSFERT);

	}
	



	
}
