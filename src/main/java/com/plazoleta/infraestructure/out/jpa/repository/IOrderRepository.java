package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByClientIdAndStatus(Long orderId, String status);

    Page<OrderEntity> findByRestaurantIdAndStatus(Long restaurantId, String status, PageRequest pageRequest);

    Optional<OrderEntity> findById(@NonNull Long id);
}
