package com.sparta.msa_exam.product.api.controller.v1.response;

import com.sparta.msa_exam.product.api.domain.ProductResult;
import java.util.List;

public record ProductResponse(Long productId, String name, int supplyPrice) {

	public static ProductResponse of(ProductResult result) {
		return new ProductResponse(result.id(), result.name(), result.supplyPrice());
	}

	public static List<ProductResponse> of(List<ProductResult> results) {
		return results.stream().map(ProductResponse::of).toList();
	}
}
