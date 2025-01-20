package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TraceRequestDto {

    private Long orderId;
    private Long clientId;
    private String clientEmail;
    private Date date;
    private String lastState;
    private String newState;
    private Long employeeId;
    private String employeeEmail;
    private Long restaurantId;

}
