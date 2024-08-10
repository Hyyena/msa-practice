package com.sparta.msa_exam.storage.db.core.order;

import static org.assertj.core.api.Assertions.assertThat;

import com.sparta.msa_exam.storage.db.CoreDbContextTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderRepositoryIT extends CoreDbContextTest {

	private final OrderRepository orderRepository;

	public OrderRepositoryIT(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Test
	void 주문이_추가된_후_주문_아이디로_조회_되어야_한다() {
		// given
		OrderEntity order = orderRepository.append(new OrderEntity("주문1"));

		// when
		OrderEntity appendedOrder = orderRepository.findById(order.getId()).orElseThrow();

		// then
		assertThat(appendedOrder.getName()).isEqualTo(order.getName());
	}

}