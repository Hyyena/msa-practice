package com.sparta.msa_exam.order.api.support.error;

import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

public enum ErrorType {

	DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.",
			LogLevel.ERROR),
	EMPTY_REQUEST_BODY(HttpStatus.BAD_REQUEST, ErrorCode.E400, "Request body is empty.", LogLevel.WARN),
	EMPTY_ORDER_PRODUCTS(HttpStatus.BAD_REQUEST, ErrorCode.E400, "주문에는 최소 하나 이상의 상품이 포함되어야 합니다.", LogLevel.WARN);

	private final HttpStatus status;

	private final ErrorCode code;

	private final String message;

	private final LogLevel logLevel;

	ErrorType(HttpStatus status, ErrorCode code, String message, LogLevel logLevel) {

		this.status = status;
		this.code = code;
		this.message = message;
		this.logLevel = logLevel;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public ErrorCode getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

}
