package com.sparta.msa_exam.product.api.config;

import com.sparta.msa_exam.product.api.support.error.ProductApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void handleUncaughtException(Throwable e, Method method, Object... params) {
		if (e instanceof ProductApiException) {
			switch (((ProductApiException) e).getErrorType().getLogLevel()) {
				case ERROR -> log.error("CoreApiException : {}", e.getMessage(), e);
				case WARN -> log.warn("CoreApiException : {}", e.getMessage(), e);
				default -> log.info("CoreApiException : {}", e.getMessage(), e);
			}
		}
		else {
			log.error("Exception : {}", e.getMessage(), e);
		}
	}

}
