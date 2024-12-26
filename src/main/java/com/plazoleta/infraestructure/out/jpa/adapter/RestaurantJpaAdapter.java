package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.dominio.model.Restaurant;
import com.plazoleta.dominio.spi.IRestaurantPersistencePort;
import com.plazoleta.infraestructure.exception.NoDataFoundException;
import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestEntityMapper restEntityMapper;

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restEntityMapper.toEntity(restaurant));
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restEntityMapper.toRestaurant(restaurantRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();
        if (restaurantEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restEntityMapper.toRestaurantList(restaurantEntityList);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
