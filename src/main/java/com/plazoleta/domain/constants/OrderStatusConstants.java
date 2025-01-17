package com.plazoleta.domain.constants;

public class OrderStatusConstants {
    public static final String PENDING = "Pendiente";
    public static final String IN_PREPARATION = "EN_PREPARACION";
    public static final String READY = "Listo";

    private OrderStatusConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
