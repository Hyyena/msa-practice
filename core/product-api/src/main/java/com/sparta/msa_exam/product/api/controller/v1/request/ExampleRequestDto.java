package com.sparta.msa_exam.product.api.controller.v1.request;

import com.sparta.msa_exam.product.api.domain.ExampleData;

public record ExampleRequestDto(String data) {
	public ExampleData toExampleData() {
		return new ExampleData(data, data);
	}
}
