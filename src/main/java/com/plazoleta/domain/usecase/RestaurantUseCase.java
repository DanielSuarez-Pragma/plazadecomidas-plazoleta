package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;

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
        long authOwner = userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner = restaurant.getOwnerId();
        userPersistencePort.getUserById(restaurant.getOwnerId());
        if (userPersistencePort.getUserById(restaurant.getOwnerId()) == 1 && authOwner == restOwner) {
            restaurantPersistencePort.saveRestaurant(restaurant);
        }else {
            throw new IllegalArgumentException("No es un owner para el restaurante");
        }
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants(int page, int size) {
        return restaurantPersistencePort.getAllRestaurants(page, size);
    }


    @Override
    public void deleteRestaurant(Long id) {
        restaurantPersistencePort.deleteRestaurant(id);
    }
}
