package com.sparta.msa_exam.order.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.order.api.support.error.EmptyOrderProductsException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderServiceTest {

	@Mock
	private OrderAppender orderAppender;

	@Mock
	private OrderProductService orderProductService;

	private OrderService orderService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		orderService = new OrderService(orderAppender, orderProductService);
	}

	@Test
	void 주문_추가_시_주문_아이디를_반환한다() {
		// given
		Order order = new Order("주문1");
		List<OrderProduct> orderProducts = List.of(new OrderProduct(101L, 100), new OrderProduct(102L, 200));
		Long orderId = 1L;
		when(orderAppender.append(order)).thenReturn(new OrderResult(orderId, order.name()));
		doNothing().when(orderProductService).append(orderId, orderProducts);

		// when
		Long result = orderService.append(order, orderProducts);

		// then
		assertThat(result).isEqualTo(orderId);
		verify(orderAppender).append(order);
		verify(orderProductService).append(1L, orderProducts);
	}

	@Test
	void 주문_추가_시_주문_상품이_없으면_예외를_발생시킨다() {
		// given
		Order order = new Order("주문1");
		List<OrderProduct> orderProducts = List.of();

		// when, then
		assertThatThrownBy(() -> orderService.append(order, orderProducts))
			.isInstanceOf(EmptyOrderProductsException.class);
	}

}