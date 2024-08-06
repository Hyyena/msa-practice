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

import com.sparta.msa_exam.product.api.domain.ProductResult;
import com.sparta.msa_exam.product.api.domain.ProductService;
import com.sparta.msa_exam.test.api.RestDocsTest;
import io.restassured.http.ContentType;
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

}