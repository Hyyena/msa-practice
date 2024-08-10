package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.storage.db.core.order.OrderEntity;

public record Order(String name) {

    public OrderEntity toEntity() {
        return new OrderEntity(name);
    }
}
