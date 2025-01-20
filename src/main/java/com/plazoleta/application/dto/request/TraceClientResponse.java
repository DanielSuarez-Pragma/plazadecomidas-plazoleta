package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TraceClientResponse {
    private Long orderId;
    private String clientEmail;
    private Date date;
    private String lastState;
    private String newState;
    private String employeeEmail;
}
