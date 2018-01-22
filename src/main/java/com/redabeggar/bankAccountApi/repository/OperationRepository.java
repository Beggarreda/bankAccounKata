package com.redabeggar.bankAccountApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redabeggar.bankAccountApi.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

	Object findByAccountAccountNumber(long anyLong);

	Object findByPayeeAccountNumber(long anyLong);



}
