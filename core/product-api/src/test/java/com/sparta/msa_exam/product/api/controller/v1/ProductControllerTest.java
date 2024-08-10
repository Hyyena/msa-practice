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

import com.sparta.msa_exam.core.enums.product.PriceStatus;
import com.sparta.msa_exam.core.enums.product.StockStatus;
import com.sparta.msa_exam.product.api.domain.PricePolicyResult;
import com.sparta.msa_exam.product.api.domain.ProductService;
import com.sparta.msa_exam.product.api.domain.ProductWithPricePolicyResult;
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
        when(productService.register(any(), any())).thenReturn(new ProductWithPricePolicyResult(1L, "상품1", 1000,
                new PricePolicyResult(1L, 1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON)));

        given().contentType(ContentType.JSON)
                .body("{\"name\":\"상품1\",\"supplyPrice\":1000, \"stock\":100}")
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
                                        fieldWithPath("supplyPrice").type(JsonFieldType.NUMBER).description("공급가격"),
                                        fieldWithPath("stock").type(JsonFieldType.NUMBER).description("재고")
                                ),
                                responseFields(
                                        fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                                        fieldWithPath("data.productId").type(JsonFieldType.NUMBER).description("상품 ID"),
                                        fieldWithPath("data.name").type(JsonFieldType.STRING).description("상품명"),
                                        fieldWithPath("data.supplyPrice").type(JsonFieldType.NUMBER)
                                                .description("공급가격"),
                                        fieldWithPath("data.pricePolicy.id").type(JsonFieldType.NUMBER)
                                                .description("가격 정책 ID"),
                                        fieldWithPath("data.pricePolicy.productId").type(JsonFieldType.NUMBER)
                                                .description("상품 ID"),
                                        fieldWithPath("data.pricePolicy.price").type(JsonFieldType.NUMBER)
                                                .description("가격"),
                                        fieldWithPath("data.pricePolicy.totalQuantity").type(JsonFieldType.NUMBER)
                                                .description("재고"),
                                        fieldWithPath("data.pricePolicy.stockStatus").type(JsonFieldType.STRING)
                                                .description("재고 상태"),
                                        fieldWithPath("data.pricePolicy.priceStatus").type(JsonFieldType.STRING)
                                                .description("가격 상태"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                                )
                        )
                );
    }

    @Test
    public void 상품_목록_조회() {
        List<ProductWithPricePolicyResult> expectedResults = List.of(
                new ProductWithPricePolicyResult(1L, "상품1", 1000,
                        new PricePolicyResult(1L, 1L, 1000, 100, StockStatus.IN_STOCK, PriceStatus.ON)),
                new ProductWithPricePolicyResult(2L, "상품2", 2000,
                        new PricePolicyResult(2L, 2L, 2000, 200, StockStatus.IN_STOCK, PriceStatus.ON)));
        when(productService.read(any(Cursor.class))).thenReturn(expectedResults);

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
                            fieldWithPath("data.result[].pricePolicy.id").type(JsonFieldType.NUMBER)
                                .description("가격 정책 ID"),
                            fieldWithPath("data.result[].pricePolicy.productId").type(JsonFieldType.NUMBER)
                                .description("상품 ID"),
                            fieldWithPath("data.result[].pricePolicy.price").type(JsonFieldType.NUMBER)
                                .description("가격"),
                            fieldWithPath("data.result[].pricePolicy.totalQuantity").type(JsonFieldType.NUMBER)
                                .description("재고"),
                            fieldWithPath("data.result[].pricePolicy.stockStatus").type(JsonFieldType.STRING)
                                .description("재고 상태"),
                            fieldWithPath("data.result[].pricePolicy.priceStatus").type(JsonFieldType.STRING)
                                .description("가격 상태"),
                            fieldWithPath("data.hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 존재 여부"),
                            fieldWithPath("error").type(JsonFieldType.NULL).ignored())));
    }

}