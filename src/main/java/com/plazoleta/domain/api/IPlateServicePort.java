package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Plate;

import java.util.List;

public interface IPlateServicePort {

    void savePlate(Plate plate);

    Plate getPlateById(Long id);

    List<Plate> getAllPlates(Long id, int page, int size);

    void updatePlate(Plate plate);

    void enableDisablePlate(Plate plate);

    void deletePlateById(Long id);
}