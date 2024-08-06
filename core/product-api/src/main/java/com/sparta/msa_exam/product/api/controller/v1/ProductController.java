package com.sparta.msa_exam.product.api.controller.v1;

import com.sparta.msa_exam.product.api.controller.v1.request.RegisterProductRequest;
import com.sparta.msa_exam.product.api.controller.v1.response.ProductResponse;
import com.sparta.msa_exam.product.api.domain.ProductResult;
import com.sparta.msa_exam.product.api.domain.ProductService;
import com.sparta.msa_exam.product.api.support.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/api/v1/product")
	public ApiResponse<ProductResponse> registerProduct(@RequestBody RegisterProductRequest request) {
		ProductResult result = productService.register(request.toProduct());
		return ApiResponse.success(ProductResponse.of(result));
	}

}
