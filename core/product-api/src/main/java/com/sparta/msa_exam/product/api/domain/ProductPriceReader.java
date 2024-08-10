package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductPriceRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceReader {

    private final ProductPriceRepository productPriceRepository;

    public ProductPriceReader(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    public List<PricePolicyResult> read(List<Long> productIds) {
        return PricePolicyResult.of(productPriceRepository.findByProductIdIn(productIds));
    }

}
