package com.plazoleta.dominio.spi;

import com.plazoleta.dominio.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long id);
    List<Restaurant> getAllRestaurants();
    void deleteRestaurant(Long id);
}
