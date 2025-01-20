package com.plazoleta.domain.constants;

public class PlateConstants {

    public static final Integer MIN_PRICE_PLATE = 0;
    public static final Boolean DEFAULT_PLATE_STATE = true;

    private PlateConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
