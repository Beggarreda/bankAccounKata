package com.redabeggar.bankAccountApi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;

import com.redabeggar.bankAccountApi.exception.AccountAlreadyExistException;
import com.redabeggar.bankAccountApi.exception.AccountNotFoundException;
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
    public void Should_Return_Account(){
        given(accountRepository.findOne(anyLong())).willReturn(account);

        Account account = accountService.getByAccountNumber(12345L);

        assertThat(account.getAccountNumber()).isEqualTo(12345);
        assertThat(account.getBalance()).isEqualTo(1500);
    }

    @Test
    public void Should_Update_Account() throws Exception {
        given(accountRepository.findOne(anyLong())).willReturn(account);
        account.setBalance(account.getBalance() + operationRequest.getAmount());
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Account updated_account = accountService.update(account);

        assertThat(updated_account.getAccountNumber()).isEqualTo(12345);
        assertThat(updated_account.getBalance()).isEqualTo(2000);
    }



    @Test
    public void Should_Create_Account() throws Exception {
        given(accountRepository.save(any(Account.class))).willReturn(account);

        Account created_account = accountService.create(account);

        assertThat(created_account.getAccountNumber()).isEqualTo(12345);
        assertThat(created_account.getBalance()).isEqualTo(1500);
    }

    @Test(expected = AccountAlreadyExistException.class)
    public void Should_Throw_account_Already_Exist_Exception_When_Creating_Account(){
        given(accountRepository.findOne(anyLong())).willReturn(account);
        Account saved_account = accountService.create(account);
    }

    @Test(expected = AccountNotFoundException.class)
    public void Should_Throw_Account_Not_Found_Exception_When_Geting_Account() throws Exception {
        given(accountRepository.findOne(anyLong())).willReturn(null);
        Account get_account = accountService.getByAccountNumber(12345);
    }


    @Test(expected = AccountNotFoundException.class)
    public void Should_Throw_Account_Not_Found_Exception_When_Updating_Account() throws Exception {
        given(accountRepository.findOne(anyLong())).willReturn(null);
        Account saved_account = accountService.update(account);
    }
    


}