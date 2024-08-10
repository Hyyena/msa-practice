package com.sparta.msa_exam.storage.db.core.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Long> {

    default OrderProductEntity append(OrderProductEntity orderProductEntity) {
        return save(orderProductEntity);
    }

}
