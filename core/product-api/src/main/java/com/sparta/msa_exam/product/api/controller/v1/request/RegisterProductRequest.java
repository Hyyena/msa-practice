package com.sparta.msa_exam.product.api.controller.v1.request;

import com.sparta.msa_exam.product.api.domain.Product;

public record RegisterProductRequest(String name, int supplyPrice) {
	public Product toProduct() {
		return new Product(name, supplyPrice);
	}
}
