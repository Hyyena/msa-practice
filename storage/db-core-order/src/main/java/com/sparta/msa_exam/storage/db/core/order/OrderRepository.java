package com.sparta.msa_exam.storage.db.core.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	default OrderEntity append(OrderEntity orderEntity) {
		return save(orderEntity);
	}

}
