package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.spi.ICategoryPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;

import java.util.List;

public class PlateUseCase implements IPlateServicePort {

    private final IPlatePersistencePort platePersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public PlateUseCase(IPlatePersistencePort platePersistencePort, ICategoryPersistencePort categoryPersistencePort, IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort userPersistencePort) {
        this.platePersistencePort = platePersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void savePlate(Plate plate) {
        // Validar que el restaurante exista
        if (restaurantPersistencePort.getRestaurant(plate.getRestaurantId()) == null) {
            throw new IllegalArgumentException("The specified restaurant does not exist.");
        }

        // Validar que la categoría exista
        if (categoryPersistencePort.getCategoryById(plate.getCategoryId()) == null) {
            throw new IllegalArgumentException("The specified category does not exist.");
        }

        // Validar precio
        if (plate.getPrice() <= 0) {
            throw new IllegalArgumentException("The price must be a positive value.");
        }

        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(plate.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new IllegalArgumentException("This restaurant does not belong to the specified user.");
        }

        // Establecer activo como true por defecto
        plate.setActive(true);

        // Guardar el plato
        platePersistencePort.savePlate(plate);
    }

    @Override
    public Plate getPlateById(Long id) {
        return platePersistencePort.getPlateById(id);
    }


    @Override
    public List<Plate> getAllPlates() {
        return platePersistencePort.getAllPlates();
    }

    @Override
    public void updatePlate(Plate plate) {
        // Obtener el plato una vez
        Plate existingPlate = platePersistencePort.getPlateById(plate.getId());

        // Verificar si el plato existe
        if (existingPlate == null) {
            throw new IllegalArgumentException("Plate not found");
        }

        // Actualizar los valores
        existingPlate.setDescription(plate.getDescription());
        existingPlate.setPrice(plate.getPrice());

        // Guardar los cambios
        platePersistencePort.updatePlate(existingPlate);
    }

    @Override
    public void enableDisablePlate(Plate plate) {
        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(plate.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new IllegalArgumentException("This restaurant does not belong to the specified user.");
        }
        Plate existingPlate = platePersistencePort.getPlateById(plate.getId());
        existingPlate.setActive(plate.getActive());
        platePersistencePort.enableDisablePlate(existingPlate);

    }


    @Override
    public void deletePlateById(Long id) {
        platePersistencePort.deletePlateById(id);
    }
}