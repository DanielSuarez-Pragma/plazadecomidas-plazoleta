package com.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "plato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoryEntity category;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "url_imagen")
    private String imageUrl;

    @Column(name = "activo")
    private Boolean active;
}