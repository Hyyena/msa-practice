package com.sparta.msa_exam.storage.db.core.order;

import com.sparta.msa_exam.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_product")
public class OrderProductEntity extends BaseEntity {

    @Column
    private Long orderId;

    @Column
    private Long productId;

    public OrderProductEntity() {
    }

    public OrderProductEntity(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

}
