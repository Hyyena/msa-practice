package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;

public record PricePolicy(Long productId, int price, int totalQuantity, StockStatus stockStatus,
        PriceStatus priceStatus) {

    public ProductPriceEntity toEntity() {
        return new ProductPriceEntity(productId, price, totalQuantity, stockStatus, priceStatus);
    }
}
