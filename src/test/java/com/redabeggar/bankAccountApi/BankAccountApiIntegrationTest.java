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

import com.redabeggar.bankAccountApi.model.Operation;
import com.redabeggar.bankAccountApi.utils.OperationRequest;
import com.redabeggar.bankAccountApi.utils.OperationType;




@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountApiIntegrationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void should_MakeADeposit() throws Exception {

		// arrange
		OperationRequest operationRequest = new OperationRequest(12345L, 1500);

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


	
}
