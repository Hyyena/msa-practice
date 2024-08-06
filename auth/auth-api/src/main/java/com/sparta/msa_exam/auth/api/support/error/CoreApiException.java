package com.sparta.msa_exam.auth.api.support.error;

public class CoreApiException extends RuntimeException {

	private final ErrorType errorType;

	private final Object data;

	public CoreApiException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
		this.data = null;
	}

	public CoreApiException(ErrorType errorType, Object data) {
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
