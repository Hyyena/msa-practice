package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.storage.db.core.product.ProductEntity;

public record Product(String name, int supplyPrice) {

    public ProductEntity toEntity() {
        return new ProductEntity(name, supplyPrice);
    }
}
