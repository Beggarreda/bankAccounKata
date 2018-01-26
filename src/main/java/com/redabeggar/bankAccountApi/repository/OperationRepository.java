package com.redabeggar.bankAccountApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redabeggar.bankAccountApi.model.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {


	List<Operation> findByAccountAccountNumber(long anyLong);

	List<Operation> findByPayeeAccountNumber(long anyLong);



}
