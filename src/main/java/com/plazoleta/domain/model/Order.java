package com.plazoleta.domain.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long clientId;
    private Date date;
    private String status;
    private Long chefId;
    private Long restaurantId;
}
