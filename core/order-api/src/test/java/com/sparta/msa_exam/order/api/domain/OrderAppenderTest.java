package com.sparta.msa_exam.order.api.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.sparta.msa_exam.storage.db.core.order.OrderEntity;
import com.sparta.msa_exam.storage.db.core.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderAppenderTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderAppender orderAppender;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderAppender = new OrderAppender(orderRepository);
    }

    @Test
    void 주문을_추가한다() {
        // given
        Order order = new Order("주문1");
        when(orderRepository.append(any(OrderEntity.class))).thenReturn(order.toEntity());

        // when
        OrderResult appendedOrder = orderAppender.append(order);

        // then
        assertNotNull(appendedOrder);
        assertThat(appendedOrder.name()).isEqualTo("주문1");
    }

}