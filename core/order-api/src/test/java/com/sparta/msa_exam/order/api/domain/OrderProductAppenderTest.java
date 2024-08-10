package com.sparta.msa_exam.order.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.storage.db.core.order.OrderProductEntity;
import com.sparta.msa_exam.storage.db.core.order.OrderProductRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderProductAppenderTest {

	@Mock
	private OrderProductRepository orderProductRepository;

	private OrderProductAppender orderProductAppender;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderProductAppender = new OrderProductAppender(orderProductRepository);
	}

	@Test
	void 주문_상품을_추가한다() {
		// given
		Long orderId = 1L;
		List<OrderProduct> orderProducts = List.of(new OrderProduct(10L, 10), new OrderProduct(20L, 20));
		List<OrderProductResult> appendedOrderProduct = List.of(new OrderProductResult(orderId, 10L),
				new OrderProductResult(orderId, 20L));
		when(orderProductRepository.saveAll(anyList()))
			.thenReturn(List.of(new OrderProductEntity(orderId, 10L), new OrderProductEntity(orderId, 20L)));

		// when
		List<OrderProductResult> results = orderProductAppender.append(orderId, orderProducts);

		// then
		assertThat(results).isNotNull();
		assertThat(results).hasSize(2);
		assertThat(results).containsExactlyElementsOf(appendedOrderProduct);
	}

}