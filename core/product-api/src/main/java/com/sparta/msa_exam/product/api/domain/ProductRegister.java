package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductRegister {

	private final ProductRepository productRepository;

	public ProductRegister(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public ProductResult add(Product product) {
		ProductEntity addedProduct = productRepository.add(product.toEntity());
		return ProductResult.of(addedProduct);
	}

}
