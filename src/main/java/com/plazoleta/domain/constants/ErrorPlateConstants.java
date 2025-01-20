package com.plazoleta.domain.constants;

public class ErrorPlateConstants {

    public static final String RESTAURANT_NOT_FOUND = "The specified restaurant does not exist.";
    public static final String RESTAURANT_NOT_BELONG = "This restaurant does not belong to the specified user.";
    public static final String CATEGORY_NOT_FOUND = "The specified category does not exist.";
    public static final String PLATE_PRICE_NOT_DESERVED = "The price must be a positive value.";
    public static final String DATA_ERROR = "The price must be a positive value.";
    public static final String PLATE_NOT_FOUND = "Plate not found";

    private ErrorPlateConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
