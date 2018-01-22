package com.redabeggar.bankAccountApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redabeggar.bankAccountApi.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



}
