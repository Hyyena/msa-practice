package com.sparta.msa_exam.storage.db.core.product;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.msa_exam.storage.db.CoreDbContextTest;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    void 상품_ID보다_큰_상품_목록을_커서_기반으로_페이지네이션하여_조회한다() {
        ProductEntity product1 = productRepository.add(new ProductEntity("product1", 1000));
        ProductEntity product2 = productRepository.add(new ProductEntity("product2", 2000));
        ProductEntity product3 = productRepository.add(new ProductEntity("product3", 3000));

        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "id"));
        List<ProductEntity> products = productRepository.findByIdGreaterThan(product1.getId(), pageable);

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).isEqualTo("product2");
        assertThat(products.get(1).getName()).isEqualTo("product3");
    }

    @Test
    void ID_순으로_정렬된_상품_목록을_페이지네이션하여_조회한다() {
        ProductEntity product1 = productRepository.add(new ProductEntity("product1", 1000));
        ProductEntity product2 = productRepository.add(new ProductEntity("product2", 2000));
        ProductEntity product3 = productRepository.add(new ProductEntity("product3", 3000));

        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.ASC, "id"));
        List<ProductEntity> products = productRepository.findAll(pageable).getContent();

        assertThat(products).hasSize(2);
        assertThat(products.get(0).getName()).isEqualTo("product1");
        assertThat(products.get(1).getName()).isEqualTo("product2");
    }

}