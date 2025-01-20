package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    PlateEntity findPlateEntityById(Long id);
    void deleteById(Long id);
    Page<PlateEntity> findByRestaurantId(Long restaurantId, PageRequest pageRequest);
}
