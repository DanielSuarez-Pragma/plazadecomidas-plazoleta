package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.infraestructure.exception.NoDataFoundException;
import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    public List<Restaurant> getAllRestaurants(int page, int size) {
        // Crear el PageRequest con ordenación alfabética
        Pageable pageRequest = PageRequest.of(page , size, Sort.by(Sort.Direction.ASC, "name"));

        // Recuperar las entidades paginadas
        Page<RestaurantEntity> restaurantEntitiesPage = restaurantRepository.findAll(pageRequest);
        // Validar si hay datos disponibles para esa página
        if (restaurantEntitiesPage.isEmpty()) {
            throw new NoDataFoundException();
        }

        // Obtener la lista de contenido
        List<RestaurantEntity> restaurants = restaurantEntitiesPage.getContent();

        // Convertir las entidades a modelo de dominio y devolver
        return restEntityMapper.toRestaurantList(restaurants);
    }



    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
