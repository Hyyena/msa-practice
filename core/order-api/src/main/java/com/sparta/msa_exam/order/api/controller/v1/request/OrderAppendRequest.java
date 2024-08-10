package com.sparta.msa_exam.order.api.controller.v1.request;

import com.sparta.msa_exam.order.api.domain.Order;
import com.sparta.msa_exam.order.api.domain.OrderProduct;
import java.util.List;

public record OrderAppendRequest(String name, List<OrderProduct> orderProducts) {
    public Order toOrder() {
        return new Order(name);
    }
}
