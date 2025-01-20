package com.plazoleta.domain.constants;

public class ErrorAuthConstants {

    public static final String INVALID_TOKEN = "INVALID TOKEN";

    private ErrorAuthConstants(){
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
