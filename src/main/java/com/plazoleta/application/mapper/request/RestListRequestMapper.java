package com.plazoleta.application.mapper.request;

import com.plazoleta.application.dto.request.RestListRequest;
import com.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestListRequestMapper {
    default Restaurant toRestaurant(RestListRequest restListRequest) {
        Restaurant restaurant = new Restaurant();

        // Asignar manualmente cada campo
        restaurant.setName(restListRequest.getName());
        restaurant.setAddress(restListRequest.getAddress());
        restaurant.setOwnerId(restListRequest.getOwnerId());
        restaurant.setPhone(restListRequest.getPhone());
        restaurant.setLogoUrl(restListRequest.getLogoUrl());
        restaurant.setNit(restListRequest.getNit());

        return restaurant;
    }

}
