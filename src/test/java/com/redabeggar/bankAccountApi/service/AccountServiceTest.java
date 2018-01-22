package com.redabeggar.bankAccountApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.redabeggar.bankAccountApi.model.Account;
import com.redabeggar.bankAccountApi.repository.AccountRepository;
import com.redabeggar.bankAccountApi.utils.OperationRequest;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
   
	@Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private AccountService accountService;

    OperationRequest operationRequest;
    Account account;
    


    @Before
    public void setUp() throws Exception {
        account = new Account(12345L,1500);
        operationRequest = new OperationRequest(12345L, 500);
    }

    @Test
    public void should_returnAnAccount(){
        given(accountRepository.findOne(anyLong())).willReturn(account);

        Account account = accountService.getAccount(12345L);

        assertThat(account.getAccountNumber()).isEqualTo(12345);
        assertThat(account.getBalance()).isEqualTo(1500);
    }
    
    @Test
    public void should_UpdateAnAccount() throws Exception {
        given(accountRepository.findOne(anyLong())).willReturn(account);
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Account updated_account = accountService.updateAccount(operationRequest);

        assertThat(updated_account.getAccountNumber()).isEqualTo(12345);
        assertThat(updated_account.getBalance()).isEqualTo(2000);
    }
    
    @Test
    public void should_UpdateAnAccount_when_withdraw() throws Exception {
        given(accountRepository.findOne(anyLong())).willReturn(account);
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Account updated_account = accountService.updateAccount_when_withdraw(operationRequest);

        assertThat(updated_account.getAccountNumber()).isEqualTo(12345);
        assertThat(updated_account.getBalance()).isEqualTo(1000);
    }
    
    @Test
    public void should_CreateAnAccount() throws Exception {
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Account created_account = accountService.createAccount(account);

        assertThat(created_account.getAccountNumber()).isEqualTo(12345);
        assertThat(created_account.getBalance()).isEqualTo(1500);
    }
    
    
}
