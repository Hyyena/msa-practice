package com.sparta.msa_exam.product.api.controller.v1.response;

import com.sparta.msa_exam.product.api.domain.ProductResult;

public record ProductResponse(Long productId, String name, int supplyPrice) {

	public static ProductResponse of(ProductResult result) {
		return new ProductResponse(result.id(), result.name(), result.supplyPrice());
	}
}
