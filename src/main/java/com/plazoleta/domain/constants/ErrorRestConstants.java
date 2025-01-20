package com.plazoleta.domain.constants;

public class ErrorRestConstants {

    public static final String NO_REST_FOUNDS = "NO RESTAURANT FOUND";
    public static final String NO_OWNER_REST = "NO OWNER FOR THIS REST";
    public static final String PHONE_ERROR = "Phone must be a valid phone number";
    public static final String PHONE_REQUIRED = "PHONE REQUIRED";
    public static final String NAME_REQUIRED = "RESTAURANT NAME REQUIRED";
    public static final String ADDRESS_REQUIRED = "RESTAURANT ADDRESS REQUIRED";
    public static final String OWNER_REQUIRED = "RESTAURANT OWNER ID REQUIRED";
    public static final String LOGO_REQUIRED = "RESTAURANT LOGO REQUIRED";
    public static final String NIT_REQUIRED = "RESTAURANT LOGO REQUIRED";
    public static final String NAME_BAD_LENGTH = "Restaurant name is or too large or too short";
    public static final String ADDRESS_BAD_LENGTH = "Restaurant address is or too large or too short";
    public static final String OWNER_BAD_LENGTH = "Restaurant owner id is or too large or too short";
    public static final String LOGO_BAD_LENGTH = "Restaurant logo is or too large or too short";
    public static final String NIT_BAD_LENGTH = "Restaurant nit is or too large or too short";
    public static final String PERMISSION_ALREADY_EXIST = "This permission already exists.";
    public static final String NO_DATA_FOUND = "NO DATA FOUND";

    private ErrorRestConstants() {
        throw new IllegalStateException(UTILITY_CLASS_EXCEPTION_MESSAGE);
    }
    public static final String UTILITY_CLASS_EXCEPTION_MESSAGE = "The utility class cannot be instantiated.";
}
