package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    Optional<RestaurantEntity> findById(Long id);
    void deleteById(Long id);
}
