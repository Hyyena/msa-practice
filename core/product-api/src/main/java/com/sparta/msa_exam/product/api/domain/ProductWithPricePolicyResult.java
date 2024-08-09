package com.sparta.msa_exam.product.api.domain;

import java.util.List;

public record ProductWithPricePolicyResult(Long productId, String name, int supplyPrice,
		PricePolicyResult pricePolicy) {

	public static ProductWithPricePolicyResult of(ProductResult productResult, PricePolicyResult pricePolicyResult) {
		return new ProductWithPricePolicyResult(productResult.id(), productResult.name(), productResult.supplyPrice(),
				pricePolicyResult);
	}

	public static List<ProductWithPricePolicyResult> of(List<ProductResult> productResults,
			List<PricePolicyResult> pricePolicyResults) {
		return productResults.stream().map(productResult -> {
			PricePolicyResult pricePolicyResult = pricePolicyResults.stream()
				.filter(pricePolicy -> pricePolicy.productId().equals(productResult.id()))
				.findFirst()
				.orElseThrow();
			return of(productResult, pricePolicyResult);
		}).toList();
	}
}
