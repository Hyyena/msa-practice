package com.sparta.msa_exam.product.api.controller.v1.request;

import com.sparta.msa_exam.product.api.domain.Product;
import com.sparta.msa_exam.product.api.domain.ProductWithStock;

public record RegisterProductRequest(String name, int supplyPrice, int stock) {
    public Product toProduct() {
        return new Product(name, supplyPrice);
    }

    public ProductWithStock toStock() {
        return new ProductWithStock(name, supplyPrice, stock);
    }
}
