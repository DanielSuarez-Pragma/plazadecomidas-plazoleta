package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.OrderPinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderPinRepository extends JpaRepository<OrderPinEntity, Long> {
}
