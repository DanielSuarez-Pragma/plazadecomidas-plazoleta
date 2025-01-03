package com.plazoleta.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
