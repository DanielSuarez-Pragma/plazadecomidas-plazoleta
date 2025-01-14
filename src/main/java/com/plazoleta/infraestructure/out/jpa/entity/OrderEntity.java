package com.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_cliente")
    private Long clientId;

    @Column(name = "fecha")
    private Date date;

    @Column(name = "estado")
    private String status;

    @Column(name = "id_chef")
    private Long chefId;

    @Column(name = "id_restaurante")
    private Long restaurantId;

}
