package com.sparta.msa_exam.order.api.controller.v1;

import com.sparta.msa_exam.order.api.controller.v1.request.OrderAppendRequest;
import com.sparta.msa_exam.order.api.controller.v1.response.OrderAppendResponse;
import com.sparta.msa_exam.order.api.domain.OrderService;
import com.sparta.msa_exam.order.api.support.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/order")
    public ApiResponse<OrderAppendResponse> appendOrder(@RequestBody OrderAppendRequest request) {
        Long orderId = orderService.append(request.toOrder(), request.orderProducts());
        return ApiResponse.success(OrderAppendResponse.of(orderId));
    }

}
