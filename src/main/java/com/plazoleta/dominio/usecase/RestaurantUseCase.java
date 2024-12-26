package com.plazoleta.dominio.usecase;

import com.plazoleta.dominio.api.IRestaurantServicePort;
import com.plazoleta.dominio.model.Restaurant;
import com.plazoleta.dominio.spi.IRestaurantPersistencePort;
import com.plazoleta.dominio.spi.IUserPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserPersistencePort userPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        userPersistencePort.getUserById(restaurant.getOwnerId());
        if (userPersistencePort.getUserById(restaurant.getOwnerId()) == 1){
            restaurantPersistencePort.saveRestaurant(restaurant);
        }else {
            throw new IllegalArgumentException("No es un owner");
        }
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantPersistencePort.deleteRestaurant(id);
    }
}
