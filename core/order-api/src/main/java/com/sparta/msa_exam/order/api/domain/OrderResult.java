package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.storage.db.core.order.OrderEntity;

public record OrderResult(Long orderId, String name) {

    public static OrderResult of(OrderEntity orderEntity) {
        return new OrderResult(orderEntity.getId(), orderEntity.getName());
    }
}
