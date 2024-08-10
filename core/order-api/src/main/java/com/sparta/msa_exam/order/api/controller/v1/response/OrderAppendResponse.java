package com.sparta.msa_exam.order.api.controller.v1.response;

public record OrderAppendResponse(Long orderId) {

    public static OrderAppendResponse of(Long orderId) {
        return new OrderAppendResponse(orderId);
    }
}
