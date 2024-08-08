package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;

public record PricePolicyResult(Long id, Long productId, int price, int totalQuantity, StockStatus stockStatus,
		PriceStatus priceStatus) {

	public static PricePolicyResult of(ProductPriceEntity productPriceEntity) {
		return new PricePolicyResult(productPriceEntity.getId(), productPriceEntity.getProductId(),
				productPriceEntity.getPrice(), productPriceEntity.getTotalQuantity(),
				productPriceEntity.getStockStatus(), productPriceEntity.getPriceStatus());
	}
}
