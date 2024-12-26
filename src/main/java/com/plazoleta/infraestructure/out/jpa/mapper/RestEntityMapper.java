package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.dominio.model.Restaurant;
import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestEntityMapper {

    // Método de depuración para convertir de modelo a entidad
    default RestaurantEntity toEntity(Restaurant restaurant) {
        if (restaurant == null) {
            return null;
        }
        return new RestaurantEntity(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getOwnerId(),
                restaurant.getPhone(),
                restaurant.getLogoUrl(),
                restaurant.getNit()
        );
    }

    // Método de depuración para convertir de entidad a modelo
    default Restaurant toRestaurant(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            return null;
        }
        return new Restaurant(
                restaurantEntity.getId(),
                restaurantEntity.getName(),
                restaurantEntity.getAddress(),
                restaurantEntity.getOwnerId(),
                restaurantEntity.getPhone(),
                restaurantEntity.getLogoUrl(),
                restaurantEntity.getNit()
        );
    }

    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntityList);

}
