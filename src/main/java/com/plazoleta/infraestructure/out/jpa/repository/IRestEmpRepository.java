package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.RestEmpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRestEmpRepository extends JpaRepository<RestEmpEntity, Long> {
    Optional<RestEmpEntity> findByRestaurantIdAndEmployeeId(Long restaurantId, Long employeeId);
}
