package com.sparta.msa_exam.storage.db.core.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	default ProductEntity add(ProductEntity productEntity) {
		return save(productEntity);
	}

}
