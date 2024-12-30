package com.plazoleta.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlateListResponse {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String categoryName;
    private String restaurantName;
    private Boolean active;
}
