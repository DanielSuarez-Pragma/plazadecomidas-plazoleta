package com.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido_pins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderPinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_pedido")
    private Long orderId;

    private String pin;

}
