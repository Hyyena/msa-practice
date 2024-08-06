package com.sparta.msa_exam.product.api.support.error;

public class ProductApiException extends RuntimeException {

	private final ErrorType errorType;

	private final Object data;

	public ProductApiException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
		this.data = null;
	}

	public ProductApiException(ErrorType errorType, Object data) {
		super(errorType.getMessage());
		this.errorType = errorType;
		this.data = data;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public Object getData() {
		return data;
	}

}
