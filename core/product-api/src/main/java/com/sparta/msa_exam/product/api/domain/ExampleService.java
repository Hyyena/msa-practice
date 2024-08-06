package com.sparta.msa_exam.product.api.domain;

import org.springframework.stereotype.Service;

@Service
public class ExampleService {

	public ExampleResult processExample(ExampleData exampleData) {
		return new ExampleResult(exampleData.value());
	}

}
