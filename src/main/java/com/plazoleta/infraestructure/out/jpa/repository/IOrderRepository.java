package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByClientIdAndStatus(Long orderId, String status);
}
