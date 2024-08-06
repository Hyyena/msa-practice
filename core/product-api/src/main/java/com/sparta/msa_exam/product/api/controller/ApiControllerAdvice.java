package com.sparta.msa_exam.product.api.controller;

import com.sparta.msa_exam.product.api.support.error.ProductApiException;
import com.sparta.msa_exam.product.api.support.error.ErrorType;
import com.sparta.msa_exam.product.api.support.response.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(ProductApiException.class)
	public ResponseEntity<ApiResponse<?>> handleCoreApiException(ProductApiException e) {
		switch (e.getErrorType().getLogLevel()) {
			case ERROR -> log.error("ProductApiException : {}", e.getMessage(), e);
			case WARN -> log.warn("ProductApiException : {}", e.getMessage(), e);
			default -> log.info("ProductApiException : {}", e.getMessage(), e);
		}
		return new ResponseEntity<>(ApiResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
		log.error("Exception : {}", e.getMessage(), e);
		return new ResponseEntity<>(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
	}

}
