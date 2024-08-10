package com.sparta.msa_exam.product.api.controller.v1.response;

import com.sparta.msa_exam.product.api.domain.PricePolicyResult;
import com.sparta.msa_exam.product.api.domain.ProductWithPricePolicyResult;
import java.util.List;

public record ProductResponse(Long productId, String name, int supplyPrice, PricePolicyResult pricePolicy) {

    public static ProductResponse of(ProductWithPricePolicyResult productWithPricePolicyResult) {
        return new ProductResponse(productWithPricePolicyResult.productId(), productWithPricePolicyResult.name(),
                productWithPricePolicyResult.supplyPrice(), productWithPricePolicyResult.pricePolicy());
    }

    public static List<ProductResponse> of(List<ProductWithPricePolicyResult> productWithPricePolicies) {
        return productWithPricePolicies.stream().map(ProductResponse::of).toList();
    }
}
