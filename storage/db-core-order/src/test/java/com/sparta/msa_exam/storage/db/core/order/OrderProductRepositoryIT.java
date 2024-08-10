package com.sparta.msa_exam.storage.db.core.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sparta.msa_exam.storage.db.CoreDbContextTest;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OrderProductRepositoryIT extends CoreDbContextTest {

    private final OrderProductRepository orderProductRepository;

    public OrderProductRepositoryIT(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    @Test
    void 주문_상품이_추가된_후_주문_상품_아이디로_조회_되어야_한다() {
        // given
        OrderProductEntity orderProduct = orderProductRepository.append(new OrderProductEntity(1L, 1L));

        // when
        OrderProductEntity appendedOrderProduct = orderProductRepository.findById(orderProduct.getId()).orElseThrow();

        // then
        assertEquals(appendedOrderProduct.getOrderId(), orderProduct.getOrderId());
        assertEquals(appendedOrderProduct.getProductId(), orderProduct.getProductId());
    }

}