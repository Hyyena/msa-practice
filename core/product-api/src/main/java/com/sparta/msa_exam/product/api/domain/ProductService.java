package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.product.api.support.Cursor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRegister productRegister;

    private final ProductReader productReader;

    private final ProductPriceRegister productPriceRegister;

    private final ProductFinder pircePolicyFinder;

    private final ProductPriceReader productPriceReader;

    public ProductService(ProductRegister productRegister, ProductReader productReader,
            ProductPriceRegister productPriceRegister, ProductFinder productFinder,
            ProductPriceReader productPriceReader) {
        this.productRegister = productRegister;
        this.productReader = productReader;
        this.productPriceRegister = productPriceRegister;
        this.pircePolicyFinder = productFinder;
        this.productPriceReader = productPriceReader;
    }

    public ProductWithPricePolicyResult register(Product product, ProductWithStock productWithStock) {
        ProductResult productResult = productRegister.add(product);
        PricePolicyResult pricePolicyResult = productPriceRegister.add(ProductResult.toPricePolicy(productResult,
                productWithStock.stock(), StockStatus.IN_STOCK, PriceStatus.ON));
        return ProductWithPricePolicyResult.of(productResult, pricePolicyResult);
    }

    public ProductWithPricePolicyResult find(Long productId) {
        return pircePolicyFinder.find(productId);
    }

    public List<ProductWithPricePolicyResult> read(Cursor cursor) {
        List<ProductResult> products = productReader.read(cursor);
        List<Long> productIds = products.stream().map(ProductResult::id).toList();
        List<PricePolicyResult> pricePolicies = productPriceReader.read(productIds);

        return ProductWithPricePolicyResult.of(products, pricePolicies);
    }

}
