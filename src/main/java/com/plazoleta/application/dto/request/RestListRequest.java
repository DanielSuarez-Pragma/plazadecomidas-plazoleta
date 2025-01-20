package com.plazoleta.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import static com.plazoleta.domain.constants.ErrorRestConstants.*;
import static com.plazoleta.domain.constants.RestaurantConstants.PHONE_REGEX;

@Getter
@Setter
public class RestListRequest {

    @NotBlank(message = NAME_REQUIRED)
    @Size(min = 1, max = 30, message = NAME_BAD_LENGTH)
    private String name;

    @NotBlank(message = ADDRESS_REQUIRED)
    @Size(min = 5, max = 30, message = ADDRESS_BAD_LENGTH)
    private String address;

    @NotBlank(message = OWNER_REQUIRED)
    @Size(max = 2, message = OWNER_BAD_LENGTH)
    private Long ownerId;

    @NotBlank(message = PHONE_REQUIRED)
    @Size(min = 10,max = 13, message = PHONE_ERROR)
    @Pattern(regexp = PHONE_REGEX, message = PHONE_ERROR)
    private String phone;

    @NotBlank(message = LOGO_REQUIRED)
    @Size(min = 1, max = 255, message = LOGO_BAD_LENGTH)
    private String logoUrl;

    @NotBlank(message = NIT_REQUIRED)
    @Size(min = 1, max = 15, message = NIT_BAD_LENGTH)
    private String nit;

}
