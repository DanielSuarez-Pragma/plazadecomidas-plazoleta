package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.exception.NoDataFoundException;
import com.plazoleta.domain.exception.RestaurantDoesnBelongException;
import com.plazoleta.domain.model.Plate;
import com.plazoleta.domain.spi.ICategoryPersistencePort;
import com.plazoleta.domain.spi.IPlatePersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;

import java.util.List;

import static com.plazoleta.domain.constants.ErrorPlateConstants.*;
import static com.plazoleta.domain.constants.PlateConstants.DEFAULT_PLATE_STATE;
import static com.plazoleta.domain.constants.PlateConstants.MIN_PRICE_PLATE;

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
            throw new InvalidErrorException(RESTAURANT_NOT_FOUND);
        }
        // Validar que la categoría exista
        if (categoryPersistencePort.getCategoryById(plate.getCategoryId()) == null) {
            throw new InvalidErrorException(CATEGORY_NOT_FOUND);
        }
        // Validar precio
        if (plate.getPrice() <= MIN_PRICE_PLATE) {
            throw new InvalidErrorException(PLATE_PRICE_NOT_DESERVED);
        }
        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(plate.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new IllegalArgumentException(new RestaurantDoesnBelongException());
        }
        // Establecer activo como true por defecto
        plate.setActive(DEFAULT_PLATE_STATE);
        // Guardar el plato
        platePersistencePort.savePlate(plate);
    }

    @Override
    public Plate getPlateById(Long id) {
        return platePersistencePort.getPlateById(id);
    }

    @Override
    public List<Plate> getAllPlates(Long restaurantId, int page, int size) {
        List<Plate> plateList = platePersistencePort.getPlatesByRestaurantId(restaurantId, page, size);
        if(plateList.isEmpty()) {
            throw new NoDataFoundException(DATA_ERROR);
        }
        return plateList;
    }

    @Override
    public void updatePlate(Plate plate) {
        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(plate.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new IllegalArgumentException(new RestaurantDoesnBelongException());
        }
        // Validar precio
        if (plate.getPrice() <= MIN_PRICE_PLATE) {
            throw new InvalidErrorException(PLATE_PRICE_NOT_DESERVED);
        }
        // Validar que el restaurante exista
        if (restaurantPersistencePort.getRestaurant(plate.getRestaurantId()) == null) {
            throw new InvalidErrorException(RESTAURANT_NOT_FOUND);
        }
        // Obtener el plato una vez
        Plate existingPlate = platePersistencePort.getPlateById(plate.getId());
        // Verificar si el plato existe
        if (existingPlate == null) {
            throw new InvalidErrorException(PLATE_NOT_FOUND);
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
            throw new InvalidErrorException(RESTAURANT_NOT_BELONG);
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