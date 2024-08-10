package com.sparta.msa_exam.product.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.core.product.ProductEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceRepository;
import com.sparta.msa_exam.storage.db.core.product.ProductRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductFinderTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductPriceRepository productPriceRepository;

    private ProductFinder productFinder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productFinder = new ProductFinder(productRepository, productPriceRepository);
    }

    @Test
    void 상품과_가격_정책을_조회한다() {
        // given
        Long productId = 1L;
        ProductEntity product = new ProductEntity("상품1", 1000);
        ReflectionTestUtils.setField(product, "id", productId);
        ProductPriceEntity pricePolicy = new ProductPriceEntity(productId, 1000, 100, StockStatus.IN_STOCK,
                PriceStatus.ON);
        when(productRepository.add(product)).thenReturn(product);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productPriceRepository.add(pricePolicy)).thenReturn(pricePolicy);
        when(productPriceRepository.findByProductId(productId)).thenReturn(pricePolicy);

        // when
        productRepository.add(product);
        productPriceRepository.add(pricePolicy);
        ProductWithPricePolicyResult result = productFinder.find(productId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.productId()).isEqualTo(productId);
        assertThat(result.pricePolicy().price()).isEqualTo(1000);
        assertThat(result.pricePolicy().totalQuantity()).isEqualTo(100);
    }

}