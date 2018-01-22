package com.redabeggar.bankAccountApi.exception;

public class AmountNotValidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	  public AmountNotValidException(String msg) {
	        System.out.println(msg);
	    }

}
