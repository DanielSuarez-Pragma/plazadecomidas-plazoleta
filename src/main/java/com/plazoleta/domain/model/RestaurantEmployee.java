package com.plazoleta.domain.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEmployee {
    private Long id;
    private Long restaurantId;
    private Long employeeId;
}
