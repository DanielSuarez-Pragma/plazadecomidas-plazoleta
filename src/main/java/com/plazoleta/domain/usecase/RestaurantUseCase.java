package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.exception.NoDataFoundException;
import com.plazoleta.domain.model.Restaurant;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;

import java.util.List;
import java.util.Objects;

import static com.plazoleta.domain.constants.ErrorRestConstants.NO_OWNER_REST;
import static com.plazoleta.domain.constants.ErrorRestConstants.NO_REST_FOUNDS;
import static com.plazoleta.domain.constants.RestaurantConstants.OWNER_ROLE_ID;

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
        if (!Objects.equals(userPersistencePort.getUserById(restaurant.getOwnerId()), OWNER_ROLE_ID) && authOwner != restOwner) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }

        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long id) {
        return restaurantPersistencePort.getRestaurant(id);
    }

    @Override
    public List<Restaurant> getAllRestaurants(int page, int size) {
        List<Restaurant> restaurants = restaurantPersistencePort.getAllRestaurants(page, size);
        if (restaurants.isEmpty()) {
            throw new NoDataFoundException(NO_REST_FOUNDS);
        }
        return restaurants;
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantPersistencePort.deleteRestaurant(id);
    }
}
