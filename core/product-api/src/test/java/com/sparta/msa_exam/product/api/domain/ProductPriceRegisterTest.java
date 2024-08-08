package com.sparta.msa_exam.product.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductPriceRegisterTest {

	@Mock
	private ProductPriceRepository productPriceRepository;

	private ProductPriceRegister productPriceRegister;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		productPriceRegister = new ProductPriceRegister(productPriceRepository);
	}

	@Test
	void 가격_정책을_등록한다() {
		// given
		PricePolicy pricePolicy = new PricePolicy(1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON);
		when(productPriceRepository.add(any(ProductPriceEntity.class))).thenReturn(pricePolicy.toEntity());

		// when
		PricePolicyResult result = productPriceRegister.add(pricePolicy);

		// then
		assertNotNull(result);
		assertEquals(result.price(), 1000);
		assertEquals(result.totalQuantity(), 100);
	}

}