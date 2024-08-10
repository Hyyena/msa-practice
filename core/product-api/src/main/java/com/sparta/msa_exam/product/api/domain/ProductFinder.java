package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductPriceRepository;
import com.sparta.msa_exam.storage.db.core.product.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductFinder {

    private final ProductRepository productRepository;

    private final ProductPriceRepository productPriceRepository;

    public ProductFinder(ProductRepository productRepository, ProductPriceRepository productPriceRepository) {
        this.productRepository = productRepository;
        this.productPriceRepository = productPriceRepository;
    }

    public ProductWithPricePolicyResult find(Long productId) {
        ProductResult product = ProductResult.of(productRepository.findById(productId).orElseThrow());
        PricePolicyResult pricePolicy = PricePolicyResult.of(productPriceRepository.findByProductId(productId));
        return ProductWithPricePolicyResult.of(product, pricePolicy);
    }

}
