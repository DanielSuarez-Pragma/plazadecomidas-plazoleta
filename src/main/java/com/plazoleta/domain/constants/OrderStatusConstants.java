package com.plazoleta.domain.constants;

public class OrderStatusConstants {
    public static final String PENDING = "PENDIENTE";
    public static final String IN_PREPARATION = "EN_PREPARACION";
    public static final String READY = "LISTO";
    public static final String DELIVERED = "DELIVERED";
    public static final String CANCELLED = "CANCELADO";

    private OrderStatusConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
