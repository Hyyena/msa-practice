package com.sparta.msa_exam.order.api.controller.v1;

import static com.sparta.msa_exam.test.api.RestDocsUtils.requestPreprocessor;
import static com.sparta.msa_exam.test.api.RestDocsUtils.responsePreprocessor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

import com.sparta.msa_exam.order.api.controller.v1.response.OrderAppendResponse;
import com.sparta.msa_exam.order.api.domain.OrderService;
import com.sparta.msa_exam.test.api.RestDocsTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.payload.JsonFieldType;

class OrderControllerTest extends RestDocsTest {

	private OrderService orderService;

	private OrderController orderController;

	@BeforeEach
	void setUp() {
		orderService = mock(OrderService.class);
		orderController = new OrderController(orderService);
		mockMvc = mockController(orderController);
	}

	@Test
    void 주문_추가() {
        when(orderService.append(any(), any())).thenReturn(new OrderAppendResponse(1L).orderId());

        given().contentType(ContentType.JSON)
                .body("{\"name\":\"주문1\", \"orderProducts\":[{\"productId\":1, \"quantity\":10}, {\"productId\":2, \"quantity\":20}]}")
                .post("/api/v1/order")
                .then()
                .status(HttpStatus.OK)
                .apply(
                        document(
                                "주문 추가",
                                requestPreprocessor(),
                                responsePreprocessor(),
                                requestFields(
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("주문명"),
                                        fieldWithPath("orderProducts").type(JsonFieldType.ARRAY)
                                                .description("주문 상품 목록"),
                                        fieldWithPath("orderProducts[].productId").type(JsonFieldType.NUMBER)
                                                .description("상품 아이디"),
                                        fieldWithPath("orderProducts[].quantity").type(JsonFieldType.NUMBER)
                                                .description("수량")
                                ),
                                responseFields(
                                        fieldWithPath("result").type(JsonFieldType.STRING).description("결과"),
                                        fieldWithPath("data.orderId").type(JsonFieldType.NUMBER).description("주문 아이디"),
                                        fieldWithPath("error").type(JsonFieldType.NULL).ignored()
                                )
                        ));
    }

}