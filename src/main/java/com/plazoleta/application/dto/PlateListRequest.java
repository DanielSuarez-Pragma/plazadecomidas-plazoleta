package com.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateListRequest {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Long categoryId;
    private Long restaurantId;
}
