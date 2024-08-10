package com.sparta.msa_exam.order.api.support.error;

public class EmptyOrderProductsException extends CoreApiException {

    public EmptyOrderProductsException() {
        super(ErrorType.EMPTY_ORDER_PRODUCTS);
    }

    public EmptyOrderProductsException(Object data) {
        super(ErrorType.EMPTY_ORDER_PRODUCTS, data);
    }

}
