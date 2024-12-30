package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    void saveRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long id);
    List<Restaurant> getAllRestaurants();
    void deleteRestaurant(Long id);
}
