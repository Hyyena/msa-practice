package com.sparta.msa_exam.product.api.controller.v1;

import static com.sparta.msa_exam.test.api.RestDocsUtils.requestPreprocessor;
import static com.sparta.msa_exam.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

import com.sparta.msa_exam.product.api.domain.ProductResult;
import com.sparta.msa_exam.product.api.domain.ProductService;
import com.sparta.msa_exam.product.api.support.Cursor;
import com.sparta.msa_exam.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

class ProductControllerTest extends RestDocsTest {

	private ProductService productService;

	private ProductController productController;

	@BeforeEach
	public void setUp() {
		productService = mock(ProductService.class);
		productController = new ProductController(productService);
		mockMvc = mockController(productController);
	}

	@Test
    public void 상품_추가() {
        when(productService.register(any())).thenReturn(new ProductResult(1L, "상품1", 1000));

        given().contentType(ContentType.JSON)
                .body("{\"name\":\"상품1\",\"supplyPrice\":1000}")
                .post("/api/v1/product")
                .then()
                .status(HttpStatus.OK)
                .apply(
                        document(
                                "상품 추가",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("상품명"),
                                        fieldWithPath("supplyPrice").type(JsonFieldType.NUMBER).description("공급가격")
                                ),
                                responseFields(
                                        fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                                        fieldWithPath("data.productId").type(JsonFieldType.NUMBER).description("상품 ID"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("상품명"),
                                        fieldWithPath("data.supplyPrice").type(JsonFieldType.NUMBER)
                                                .description("공급가격"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                                )
                        )
                );
    }

	@Test
	public void 상품_목록_조회() {
		List<ProductResult> productResults = List.of(new ProductResult(1L, "상품1", 1000),
				new ProductResult(2L, "상품2", 2000));
		when(productService.read(any(Cursor.class))).thenReturn(productResults);

		given().contentType(ContentType.JSON)
			.queryParam("cursor", "0")
			.queryParam("limit", "10")
			.queryParam("sortKey", "id")
			.queryParam("sort", "ASC")
			.get("/api/v1/products")
			.then()
			.status(HttpStatus.OK)
			.apply(document("상품 목록 조회", requestPreprocessor(), responsePreprocessor(),
					queryParameters(parameterWithName("cursor").description("커서 ID").optional(),
							parameterWithName("limit").description("페이지 크기"),
							parameterWithName("sortKey").description("정렬 키"),
							parameterWithName("sort").description("정렬 방향")),
					responseFields(fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
							fieldWithPath("data.result").type(JsonFieldType.ARRAY).description("상품 목록"),
							fieldWithPath("data.result[].productId").type(JsonFieldType.NUMBER).description("상품 ID"),
							fieldWithPath("data.result[].name").type(JsonFieldType.STRING).description("상품명"),
							fieldWithPath("data.result[].supplyPrice").type(JsonFieldType.NUMBER).description("공급가격"),
							fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부"),
							fieldWithPath("error").type(JsonFieldType.NULL).ignored())));
	}

}