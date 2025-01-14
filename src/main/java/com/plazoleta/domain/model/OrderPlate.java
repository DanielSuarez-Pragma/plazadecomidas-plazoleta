package com.plazoleta.domain.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlate {
    private Long id;
    private Long orderId;
    private Long plateId;
    private Integer quantity;
}
