package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long id);
    List<Restaurant> getAllRestaurants(int page, int size);
    void deleteRestaurant(Long id);
}
