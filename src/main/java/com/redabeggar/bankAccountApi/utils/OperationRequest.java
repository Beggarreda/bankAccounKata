package com.redabeggar.bankAccountApi.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class OperationRequest {

	@NonNull
	private Long accountNumber;
	@NonNull
	private double amount;



}
