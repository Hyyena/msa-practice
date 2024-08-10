package com.sparta.msa_exam.order.api.domain;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderProductService {

    private final OrderProductAppender orderProductAppender;

    public OrderProductService(OrderProductAppender orderProductAppender) {
        this.orderProductAppender = orderProductAppender;
    }

    public void append(Long orderId, List<OrderProduct> orderProducts) {
        orderProductAppender.append(orderId, orderProducts);
    }

}
