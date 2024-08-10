package com.sparta.msa_exam.order.api.domain;

import com.sparta.msa_exam.order.api.support.error.EmptyOrderProductsException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

	private final OrderAppender orderAppender;

	private final OrderProductService orderProductService;

	public OrderService(OrderAppender orderAppender, OrderProductService orderProductService) {
		this.orderAppender = orderAppender;
		this.orderProductService = orderProductService;
	}

	@Transactional
	public Long append(Order order, List<OrderProduct> orderProducts) {
		// TODO: 검증 클래스 추가
		if (orderProducts.isEmpty()) {
			throw new EmptyOrderProductsException();
		}
		OrderResult orderResult = orderAppender.append(order);
		orderProductService.append(orderResult.orderId(), orderProducts);
		return orderResult.orderId();
	}

}
