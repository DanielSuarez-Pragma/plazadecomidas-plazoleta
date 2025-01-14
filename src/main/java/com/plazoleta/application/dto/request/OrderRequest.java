package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<OrderPlateRequest> plates;
    private Long restaurantId;

}
