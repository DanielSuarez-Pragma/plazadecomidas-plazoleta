package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Plate;

import java.util.List;

public interface IPlatePersistencePort {

    // Guardar un nuevo plato
    void savePlate(Plate plate);

    // Obtener un plato por ID
    Plate getPlateById(Long id);

    // Obtener todos los platos
    List<Plate> getAllPlates();

    //Editar los platos
    void updatePlate(Plate plate);

    void enableDisablePlate(Plate plate);

    // Eliminar un plato por ID
    void deletePlateById(Long id);

    List<Plate> getPlatesByRestaurantId(Long restaurantId, int page, int size);
}
