package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.plazoleta.domain.constants.RestaurantConstants.NAME;

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
        return restEntityMapper.toRestaurant(restaurantRepository.findRestaurantEntityById(id));
    }

    @Override
    public List<Restaurant> getAllRestaurants(int page, int size) {
        Pageable pageRequest = PageRequest.of(page , size, Sort.by(Sort.Direction.ASC, NAME));
        Page<RestaurantEntity> restaurantEntitiesPage = restaurantRepository.findAll(pageRequest);
        List<RestaurantEntity> restaurants = restaurantEntitiesPage.getContent();
        return restEntityMapper.toRestaurantList(restaurants);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
