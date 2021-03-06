package com.pearson.store_backend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pearson.store_backend.dto.StoreResponse;
import com.pearson.store_backend.exception.StoreException;

@ControllerAdvice
public class StoreControllerAdvice {
	@ExceptionHandler(StoreException.class)
	public ResponseEntity<StoreResponse> getException(HttpServletRequest request, Exception exception) {
		return new ResponseEntity(new StoreResponse( exception.getMessage(),true, null,null),HttpStatus.NOT_FOUND );

	}
}