package com.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateUpdateRequest {
    private Long id;
    private String description;
    private Double price;
}
