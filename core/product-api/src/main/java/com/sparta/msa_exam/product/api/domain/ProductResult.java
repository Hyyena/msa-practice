package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductEntity;

public record ProductResult(Long id, String name, int supplyPrice) {

	public static ProductResult of(ProductEntity productEntity) {
		return new ProductResult(productEntity.getId(), productEntity.getName(), productEntity.getSupplyPrice());
	}
}
