package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.OrderPlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderPlateRepository extends JpaRepository<OrderPlateEntity, Long> {
}
