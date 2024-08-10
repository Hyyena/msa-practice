package com.sparta.msa_exam.product.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.product.api.support.Cursor;
import com.sparta.msa_exam.storage.db.core.product.ProductEntity;
import com.sparta.msa_exam.storage.db.core.product.ProductRepository;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ProductReaderTest {

    @Mock
    private ProductRepository productRepository;

    private ProductReader productReader;

    private List<ProductEntity> products;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productReader = new ProductReader(productRepository);

        // 50개의 테스트용 상품 데이터 생성
        products = IntStream.rangeClosed(1, 50).mapToObj(i -> {
            ProductEntity product = new ProductEntity("상품" + i, i * 1000);
            ReflectionTestUtils.setField(product, "id", (long) i);
            return product;
        }).toList();
    }

    @Test
    void 커서가_없을_때_첫_페이지_상품_목록을_조회한다() {
        // given
        int pageSize = 10;
        Cursor cursor = new Cursor(null, pageSize, "id", Sort.Direction.ASC);
        when(productRepository.findAll(any(Pageable.class)))
            .thenReturn(new PageImpl<>(products.subList(0, pageSize), Pageable.ofSize(pageSize), products.size()));

        // when
        List<ProductResult> results = productReader.read(cursor);

        // then
        assertThat(results).hasSize(pageSize);
        assertThat(results.get(0).name()).isEqualTo("상품1");
        assertThat(results.get(0).supplyPrice()).isEqualTo(1000);
        assertThat(results.get(9).name()).isEqualTo("상품10");
        assertThat(results.get(9).supplyPrice()).isEqualTo(10000);
    }

    @Test
    void 커서가_있을_때_다음_페이지의_상품_목록을_조회한다() {
        // given
        int pageSize = 10;
        String cursorId = "10";
        Cursor cursor = new Cursor(cursorId, pageSize, "id", Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(0, cursor.limit() + 1, Sort.by(cursor.sort(), cursor.sortKey()));
        when(productRepository.findByIdGreaterThan(Long.valueOf(cursor.cursor()), pageable))
            .thenReturn(products.subList(10, 20));

        // when
        List<ProductResult> results = productReader.read(cursor);

        // then
        assertThat(results).hasSize(pageSize);
        assertThat(results.get(0).name()).isEqualTo("상품11");
        assertThat(results.get(0).supplyPrice()).isEqualTo(11000);
        assertThat(results.get(9).name()).isEqualTo("상품20");
        assertThat(results.get(9).supplyPrice()).isEqualTo(20000);
    }

    @Test
    void 마지막_페이지_조회_시_남은_상품_목록만_조회된다() {
        // given
        int pageSize = 10;
        String cursorId = "45";
        Cursor cursor = new Cursor(cursorId, pageSize, "id", Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(0, cursor.limit() + 1, Sort.by(cursor.sort(), cursor.sortKey()));
        when(productRepository.findByIdGreaterThan(Long.valueOf(cursor.cursor()), pageable))
            .thenReturn(products.subList(45, 50));

        // when
        List<ProductResult> results = productReader.read(cursor);

        // then
        assertThat(results).hasSize(5);
        assertThat(results.get(0).name()).isEqualTo("상품46");
        assertThat(results.get(0).supplyPrice()).isEqualTo(46000);
        assertThat(results.get(4).name()).isEqualTo("상품50");
        assertThat(results.get(4).supplyPrice()).isEqualTo(50000);
    }

    @Test
    void 마지막_페이지_이후_상품_목록을_조회하면_빈_목록이_반환된다() {
        // given
        int pageSize = 10;
        String cursorId = "50";
        Cursor cursor = new Cursor(cursorId, pageSize, "id", Sort.Direction.ASC);
        Pageable pageable = PageRequest.of(0, cursor.limit() + 1, Sort.by(cursor.sort(), cursor.sortKey()));
        when(productRepository.findByIdGreaterThan(Long.valueOf(cursor.cursor()), pageable)).thenReturn(List.of());

        // when
        List<ProductResult> results = productReader.read(cursor);

        // then
        assertThat(results).isEmpty();
    }

}