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
public class TraceModel {
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
