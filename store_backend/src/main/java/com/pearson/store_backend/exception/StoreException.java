package com.pearson.store_backend.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class StoreException extends RuntimeException{

	 private String msg;

	public StoreException(String msg) {
		super();
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
	 
	
}
