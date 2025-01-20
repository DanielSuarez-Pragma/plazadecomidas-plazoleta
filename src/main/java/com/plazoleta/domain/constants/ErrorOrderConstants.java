package com.plazoleta.domain.constants;

public class ErrorOrderConstants {

    public static final String ORDER_EXIST = "NO RESTAURANT FOUND";
    public static final String REST_ID_MISMATCH = "Restaurant id mismatch";
    public static final String PLATE_NOT_ACTIVE = "Plate is not active";
    public static final String ORDER_HAS_CHEF = "The order has chef";
    public static final String ORDER_NOT_PENDING = "Plate is not active";
    public static final String ORDER_NOT_IN_PREPARATION = "Plate is not active";
    public static final String ORDER_NOT_READY = "Plate is not active";
    public static final String ORDER_NOT_PIN = "Plate is not active";
    public static final String ORDER_NOT_PENDING_OR_CANCELLED = "Lo sentimos, tu pedido ya está en preparación o fue entregado y no puede cancelarse";
    public static final String ORDER_NOT_CANCELLED = "Lo sentimos, tu pedido ya está cancelado";
    public static final String ORDER_CANCELLED = "Pedido cancelado";

    private ErrorOrderConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
