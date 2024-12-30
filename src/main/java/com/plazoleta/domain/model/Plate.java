package com.plazoleta.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plate {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private Double price;
    private Long restaurantId;
    private String imageUrl;
    private Boolean active;
}
