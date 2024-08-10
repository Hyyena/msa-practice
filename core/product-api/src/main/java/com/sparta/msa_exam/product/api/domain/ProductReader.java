package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.product.api.support.Cursor;
import com.sparta.msa_exam.storage.db.core.product.ProductRepository;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductReader {

    private final ProductRepository productRepository;

    public ProductReader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResult> read(Cursor cursor) {
        Pageable pageable = PageRequest.of(0, cursor.limit() + 1, Sort.by(cursor.sort(), cursor.sortKey()));
        if (cursor.cursor() != null) {
            return ProductResult.of(productRepository.findByIdGreaterThan(Long.parseLong(cursor.cursor()), pageable));
        }
        else {
            return ProductResult.of(productRepository.findAll(pageable).getContent());
        }

    }

}
