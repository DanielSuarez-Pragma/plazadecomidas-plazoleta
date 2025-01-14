package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderPlateRequest {

    private Long plateId;
    private int quantity;

}
