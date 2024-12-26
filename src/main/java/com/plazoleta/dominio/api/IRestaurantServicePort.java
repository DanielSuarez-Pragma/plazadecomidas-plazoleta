package com.plazoleta.dominio.api;

import com.plazoleta.dominio.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long id);
    List<Restaurant> getAllRestaurants();
    void deleteRestaurant(Long id);
}
