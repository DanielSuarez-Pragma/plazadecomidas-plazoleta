package com.plazoleta.domain.constants;

public class RestaurantConstants {

    public static final String NAME = "name";
    public static final String PHONE_REGEX = "^(\\+57\\d{10}|\\d{10})$";
    public static final Long OWNER_ROLE_ID = 1L;

    private RestaurantConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}