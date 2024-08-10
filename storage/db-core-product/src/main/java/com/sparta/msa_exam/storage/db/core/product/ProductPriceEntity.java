package com.sparta.msa_exam.storage.db.core.product;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.storage.db.core.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_price")
public class ProductPriceEntity extends BaseEntity {

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int price;

    @Column
    private int totalQuantity;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceStatus priceStatus;

    public ProductPriceEntity() {
    }

    public ProductPriceEntity(Long productId, int price, int totalQuantity, StockStatus stockStatus,
            PriceStatus priceStatus) {
        this.productId = productId;
        this.price = price;
        this.totalQuantity = totalQuantity;
        this.stockStatus = stockStatus;
        this.priceStatus = priceStatus;
    }

    public Long getProductId() {
        return productId;
    }

    public int getPrice() {
        return price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public String getStockStatusDescription() {
        return stockStatus.getDescription();
    }

    public PriceStatus getPriceStatus() {
        return priceStatus;
    }

    public String getPriceStatusDescription() {
        return priceStatus.getDescription();
    }

}
