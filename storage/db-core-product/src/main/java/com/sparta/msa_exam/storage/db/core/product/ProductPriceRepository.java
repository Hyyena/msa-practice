package com.sparta.msa_exam.storage.db.core.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPriceEntity, Long> {

    default ProductPriceEntity add(ProductPriceEntity productPriceEntity) {
        return save(productPriceEntity);
    }

    ProductPriceEntity findByProductId(Long productId);

    List<ProductPriceEntity> findByProductIdIn(List<Long> productIds);

}
