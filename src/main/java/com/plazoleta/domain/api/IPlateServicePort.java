package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Plate;

import java.util.List;

public interface IPlateServicePort {

    void savePlate(Plate plate);

    Plate getPlateById(Long id);

    List<Plate> getAllPlates();

    void deletePlateById(Long id);
}