package com.sparta.msa_exam.client.example;

import com.sparta.msa_exam.client.example.model.ExampleClientResult;

record ExampleResponseDto(String exampleResponseValue) {
	ExampleClientResult toResult() {
		return new ExampleClientResult(exampleResponseValue);
	}
}
