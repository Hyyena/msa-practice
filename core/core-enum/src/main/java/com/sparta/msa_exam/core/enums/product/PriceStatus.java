package com.sparta.msa_exam.core.enums.product;

public enum PriceStatus {

	ON("판매중"), OFF("판매중지"), SOLD_OUT("품절");

	private final String description;

	PriceStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
