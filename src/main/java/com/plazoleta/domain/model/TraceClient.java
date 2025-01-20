package com.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TraceClient {
    private Long orderId;
    private String clientEmail;
    private Date date;
    private String lastState;
    private String newState;
    private String employeeEmail;
}
