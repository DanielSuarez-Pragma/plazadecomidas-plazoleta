package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestListRequest;
import com.plazoleta.application.dto.response.RestListResponse;
import com.plazoleta.application.mapper.RestListRequestMapper;
import com.plazoleta.application.mapper.RestListResponseMapper;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestListHandler implements IRestListHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final RestListResponseMapper restListResponseMapper;
    private final RestListRequestMapper restListRequestMapper;

    public RestListHandler(IRestaurantServicePort restaurantServicePort, RestListResponseMapper restListResponseMapper, RestListRequestMapper restListRequestMapper) {
        this.restaurantServicePort = restaurantServicePort;
        this.restListResponseMapper = restListResponseMapper;
        this.restListRequestMapper = restListRequestMapper;
    }

    @Override
    public void saveRestInList(RestListRequest restListRequest) {
        Restaurant restaurant = restListRequestMapper.toRestaurant(restListRequest);
        restaurantServicePort.saveRestaurant(restaurant);

    }

    @Override
    public RestListResponse getRestFromList(Long id) {
        Restaurant restaurant = restaurantServicePort.getRestaurant(id);
        return restListResponseMapper.toResponse(restaurant);
    }

    @Override
    public List<RestListResponse> getAllRestFromList() {
        return restListResponseMapper.toResposeList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public void deleteRestFromList(Long id) {
        restaurantServicePort.deleteRestaurant(id);
    }
}
