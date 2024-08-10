package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.storage.db.core.order.OrderProductEntity;
import com.sparta.msa_exam.storage.db.core.order.OrderProductRepository;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrderProductAppender {

	private final OrderProductRepository orderProductRepository;

	public OrderProductAppender(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	public List<OrderProductResult> append(Long orderId, List<OrderProduct> orderProducts) {
		List<OrderProductEntity> orderProductEntities = orderProducts.stream()
			.map(orderProduct -> new OrderProductEntity(orderId, orderProduct.productId()))
			.toList();
		List<OrderProductEntity> appendedOrderProduct = orderProductRepository.saveAll(orderProductEntities);
		return appendedOrderProduct.stream().map(OrderProductResult::of).toList();
	}

}
