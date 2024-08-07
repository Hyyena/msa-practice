package com.sparta.msa_exam.product.api.domain;

import com.sparta.msa_exam.product.api.support.Cursor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRegister productRegister;

	private final ProductReader productReader;

	public ProductService(ProductRegister productRegister, ProductReader productReader) {
		this.productRegister = productRegister;
		this.productReader = productReader;
	}

	public ProductResult register(Product product) {
		return productRegister.add(product);
	}

	public List<ProductResult> read(Cursor cursor) {
		return productReader.read(cursor);
	}

}
