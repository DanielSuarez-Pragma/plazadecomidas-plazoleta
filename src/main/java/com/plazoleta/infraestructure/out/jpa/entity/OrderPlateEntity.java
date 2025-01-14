package com.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedidos_platos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pedido", nullable = false)
    private Long orderId;

    @Column(name = "id_plato", nullable = false)
    private Long plateId;

    @Column(name = "cantidad")
    private Integer quantity;
}
