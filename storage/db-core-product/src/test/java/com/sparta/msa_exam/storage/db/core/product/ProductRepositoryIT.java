package com.sparta.msa_exam.storage.db.core.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.msa_exam.storage.db.CoreDbContextTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductRepositoryIT extends CoreDbContextTest {

	private final ProductRepository productRepository;

	public ProductRepositoryIT(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Test
	public void 상품이_추가된_후_상품_아이디로_조회_되어야_한다() {
		ProductEntity product = productRepository.add(new ProductEntity("product", 1000));
		assertThat(product).isNotNull();

		ProductEntity addedProduct = productRepository.findById(product.getId()).get();
		assertThat(addedProduct.getName()).isEqualTo("product");
	}

}