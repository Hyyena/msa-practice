package com.sparta.msa_exam.product.api.domain;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRegister productRegister;

	public ProductService(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}

	public ProductResult register(Product product) {
		return productRegister.add(product);
	}

}
