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

	public ProductService(ProductRegister productRegister, ProductReader productReader,
			ProductPriceRegister productPriceRegister) {
		this.productRegister = productRegister;
		this.productReader = productReader;
		this.productPriceRegister = productPriceRegister;
	}

	public ProductResult register(Product product, ProductWithStock productWithStock) {
		ProductResult productResult = productRegister.add(product);
		PricePolicyResult pricePolicyResult = productPriceRegister.add(ProductResult.toPricePolicy(productResult,
				productWithStock.stock(), StockStatus.IN_STOCK, PriceStatus.ON));
		return productResult;
	}

	public List<ProductResult> read(Cursor cursor) {
		return productReader.read(cursor);
	}

}
