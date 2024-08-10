package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.storage.db.core.order.OrderProductEntity;

public record OrderProductResult(Long orderId, Long productId) {

    public static OrderProductResult of(OrderProductEntity orderProductEntity) {
        return new OrderProductResult(orderProductEntity.getOrderId(), orderProductEntity.getProductId());
    }
}
