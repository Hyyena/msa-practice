package com.sparta.msa_exam.product.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.product.api.support.Cursor;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceTest {

	@Mock
	private ProductReader productReader;

	@Mock
	private ProductRegister productRegister;

	@Mock
	private ProductPriceRegister productPriceRegister;

	private ProductService productService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		productService = new ProductService(productRegister, productReader, productPriceRegister);
	}

	@Test
	void 상품을_재고_수량만큼_추가한다() {
		// given
		Product product = new Product("상품1", 1000);
		ProductWithStock productWithStock = new ProductWithStock(product.name(), product.supplyPrice(), 100);
		PricePolicy pricePolicy = new PricePolicy(1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON);
		when(productRegister.add(product)).thenReturn(ProductResult.of(product.toEntity()));
		when(productPriceRegister.add(pricePolicy)).thenReturn(PricePolicyResult.of(pricePolicy.toEntity()));

		// when
		ProductResult result = productService.register(product, productWithStock);

		// then
		assertThat(result).isNotNull();
		assertThat(result.name()).isEqualTo("상품1");
		verify(productRegister).add(product);
		verify(productPriceRegister).add(any());
	}

	@Test
	void 상품_목록을_조회한다() {
		// given
		Cursor cursor = new Cursor(null, 10, "id", Sort.Direction.ASC);
		List<ProductResult> expectedResults = List.of(new ProductResult(1L, "상품1", 1000),
				new ProductResult(2L, "상품2", 2000));
		when(productReader.read(any(Cursor.class))).thenReturn(expectedResults);

		// when
		List<ProductResult> results = productService.read(cursor);

		// then
		assertThat(results).isEqualTo(expectedResults);
		verify(productReader).read(cursor);
	}

}