package com.sparta.msa_exam.storage.db.core.order;

import com.sparta.msa_exam.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column
    private String name;

    public OrderEntity() {
    }

    public OrderEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
