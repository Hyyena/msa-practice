package com.sparta.msa_exam.storage.db.core.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Long> {

	default ProductPriceEntity add(ProductPriceEntity productPriceEntity) {
		return save(productPriceEntity);
	}

}
