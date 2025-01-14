package com.plazoleta.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurante_empleados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestEmpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_restaurante")
    private Long restaurantId;

    @Column(name = "id_empleado")
    private Long employeeId;
}
