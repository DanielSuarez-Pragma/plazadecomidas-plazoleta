package com.plazoleta.infraestructure.out.jpa.repository;

import com.plazoleta.infraestructure.out.jpa.entity.PlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlateRepository extends JpaRepository<PlateEntity, Long> {
    Optional<PlateEntity> findById(Long id);
    void deleteById(Long id);
}
