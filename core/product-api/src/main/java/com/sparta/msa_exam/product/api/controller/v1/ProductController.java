package com.sparta.msa_exam.product.api.controller.v1;

import com.sparta.msa_exam.product.api.controller.v1.request.RegisterProductRequest;
import com.sparta.msa_exam.product.api.controller.v1.response.ProductResponse;
import com.sparta.msa_exam.product.api.domain.ProductResult;
import com.sparta.msa_exam.product.api.domain.ProductService;
import com.sparta.msa_exam.product.api.support.Cursor;
import com.sparta.msa_exam.product.api.support.response.ApiResponse;
import com.sparta.msa_exam.product.api.support.response.SliceResult;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	// TODO: 상품 다건 추가 구현
	@PostMapping("/api/v1/product")
	public ApiResponse<ProductResponse> registerProduct(@RequestBody RegisterProductRequest request) {
		ProductResult result = productService.register(request.toProduct());
		return ApiResponse.success(ProductResponse.of(result));
	}

	// TODO: 상품 단건 조회 구현
	@GetMapping("/api/v1/product")
	public ApiResponse<ProductResponse> getProduct() {
		return null;
	}

	@GetMapping("/api/v1/products")
	public ApiResponse<SliceResult<List<ProductResponse>>> readProducts(@RequestParam(required = false) String cursor,
			@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "id") String sortKey,
			@RequestParam(defaultValue = "ASC") Sort.Direction sort) {
		List<ProductResult> products = productService.read(new Cursor(cursor, limit, sortKey, sort));
		return ApiResponse.success(SliceResult.of(ProductResponse.of(products), limit));
	}

}
