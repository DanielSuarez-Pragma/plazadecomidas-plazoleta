package com.plazoleta.application.mapper.response;

import com.plazoleta.application.dto.response.RestListResponse;
import com.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestListResponseMapper {

    default RestListResponse toResponse(Restaurant restaurant) {
        if (restaurant == null) {
            return null; // Manejo de nulos
        }
        return getRestListResponse(restaurant);
    }

    private RestListResponse getRestListResponse(Restaurant restaurant) {
        RestListResponse response = new RestListResponse();
        response.setName(restaurant.getName());
        response.setLogoUrl(restaurant.getLogoUrl());
        return response;
    }


    default List<RestListResponse> toResposeList(List<Restaurant> restList) {
        return restList.stream().map(this::getRestListResponse).toList();
    }

}
