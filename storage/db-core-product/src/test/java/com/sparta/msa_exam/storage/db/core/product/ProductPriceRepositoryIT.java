package com.sparta.msa_exam.storage.db.core.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.CoreDbContextTest;
import org.junit.jupiter.api.Test;

class ProductPriceRepositoryIT extends CoreDbContextTest {

	private final ProductPriceRepository productPriceRepository;

	public ProductPriceRepositoryIT(ProductPriceRepository productPriceRepository) {
		this.productPriceRepository = productPriceRepository;
	}

	@Test
	void 가격_정책이_추가된_후_가격_정책_아이디로_조회_되어야_한다() {
		ProductPriceEntity productPrice = productPriceRepository
			.add(new ProductPriceEntity(1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON));
		assertNotNull(productPrice);

		ProductPriceEntity addedProductPrice = productPriceRepository.findById(productPrice.getId()).get();
		assertEquals(addedProductPrice.getTotalQuantity(), 100);
	}

}