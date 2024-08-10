package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.storage.db.core.order.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderAppender {

	private final OrderRepository orderRepository;

	public OrderAppender(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public OrderResult append(Order order) {
		return OrderResult.of(orderRepository.append(order.toEntity()));
	}

}
