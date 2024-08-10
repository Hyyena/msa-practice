package com.sparta.msa_exam.product.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.product.api.support.Cursor;
import com.sparta.msa_exam.storage.db.core.product.ProductEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductPriceEntity;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductServiceTest {

    @Mock
    private ProductReader productReader;

    @Mock
    private ProductRegister productRegister;

    @Mock
    private ProductPriceRegister productPriceRegister;

    @Mock
    private ProductFinder productFinder;

    @Mock
    private ProductPriceReader productPriceReader;

    private ProductService productService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRegister, productReader, productPriceRegister, productFinder,
                productPriceReader);
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
        ProductWithPricePolicyResult result = productService.register(product, productWithStock);

        // then
        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("상품1");
        verify(productRegister).add(product);
        verify(productPriceRegister).add(any());
    }

    @Test
    void 상품을_조회한다() {
        // given
        Long productId = 1L;
        ProductEntity product = new ProductEntity("상품1", 1000);
        ReflectionTestUtils.setField(product, "id", productId);
        ProductPriceEntity pricePolicy = new ProductPriceEntity(productId, 1000, 100, StockStatus.IN_STOCK,
                PriceStatus.ON);
        when(productFinder.find(productId))
            .thenReturn(ProductWithPricePolicyResult.of(ProductResult.of(product), PricePolicyResult.of(pricePolicy)));

        // when
        ProductWithPricePolicyResult result = productService.find(productId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.productId()).isEqualTo(productId);
        verify(productFinder).find(productId);
    }

    @Test
    void 상품_목록을_조회한다() {
        // given
        Cursor cursor = new Cursor(null, 10, "id", Sort.Direction.ASC);
        List<ProductResult> products = List.of(new ProductResult(1L, "상품1", 1000), new ProductResult(2L, "상품2", 2000));
        List<PricePolicyResult> pricePolicies = List.of(
                new PricePolicyResult(1L, 1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON),
                new PricePolicyResult(2L, 2L, 2000, 200, StockStatus.IN_STOCK, PriceStatus.ON));
        List<ProductWithPricePolicyResult> expectedResults = List.of(
                new ProductWithPricePolicyResult(1L, "상품1", 1000,
                        new PricePolicyResult(1L, 1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON)),
                new ProductWithPricePolicyResult(2L, "상품2", 2000,
                        new PricePolicyResult(2L, 2L, 2000, 200, StockStatus.IN_STOCK, PriceStatus.ON)));
        when(productReader.read(any(Cursor.class))).thenReturn(products);
        when(productPriceReader.read(List.of(1L, 2L))).thenReturn(pricePolicies);

        // when
        List<ProductWithPricePolicyResult> results = productService.read(cursor);

        // then
        assertThat(results).isEqualTo(expectedResults);
        verify(productReader).read(cursor);
    }

}