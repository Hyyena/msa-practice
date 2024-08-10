package com.sparta.msa_exam.storage.db.core.product;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    default ProductEntity add(ProductEntity productEntity) {
        return save(productEntity);
    }

    List<ProductEntity> findByIdGreaterThan(Long id, Pageable pageable);

}
