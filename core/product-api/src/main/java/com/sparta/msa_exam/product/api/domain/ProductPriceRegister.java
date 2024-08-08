package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductPriceRegister {

	private final ProductPriceRepository productPriceRepository;

	public ProductPriceRegister(ProductPriceRepository productPriceRepository) {
		this.productPriceRepository = productPriceRepository;
	}

	public PricePolicyResult add(PricePolicy pricePolicy) {
		ProductPriceEntity addedProductPrice = productPriceRepository.add(pricePolicy.toEntity());
		return PricePolicyResult.of(addedProductPrice);
	}

}
