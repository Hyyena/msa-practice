package com.sparta.msa_exam.core.enums.product;

public enum StockStatus {

	IN_STOCK("재고 있음"), LOW_STOCK("재고 부족"), OUT_OF_STOCK("품절");

	private final String description;

	StockStatus(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
