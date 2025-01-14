package com.plazoleta.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private Long clientId;
    private Date date;
    private String status;
    private Long chefId;
    private Long restaurantId;
}
