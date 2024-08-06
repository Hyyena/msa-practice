package com.sparta.msa_exam.storage.db.core.product;

import com.sparta.msa_exam.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

	@Column
	private String name;

	@Column
	private int supplyPrice;

	public ProductEntity() {
	}

	public ProductEntity(String name, int supplyPrice) {
		this.name = name;
		this.supplyPrice = supplyPrice;
	}

	public String getName() {
		return name;
	}

	public int getSupplyPrice() {
		return supplyPrice;
	}

}
