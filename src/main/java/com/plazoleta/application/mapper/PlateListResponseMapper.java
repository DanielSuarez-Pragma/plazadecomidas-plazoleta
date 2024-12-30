package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.PlateListResponse;
import com.plazoleta.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlateListResponseMapper {
    default PlateListResponse toResponse(Plate plate) {
        if (plate == null) {
            return null;
        }

        PlateListResponse response = new PlateListResponse();
        response.setName(plate.getName());
        response.setDescription(plate.getDescription());
        response.setPrice(plate.getPrice());
        response.setImageUrl(plate.getImageUrl());
        response.setCategoryName(plate.getCategoryId()+"");
        response.setRestaurantName(plate.getRestaurantId()+"");
        response.setActive(plate.getActive());

        return response;
    }

    default PlateListResponse toResponseDetailed(Plate plate, String categoryName, String restaurantName) {
        if (plate == null) {
            return null;
        }

        PlateListResponse response = new PlateListResponse();
        response.setName(plate.getName());
        response.setDescription(plate.getDescription());
        response.setPrice(plate.getPrice());
        response.setImageUrl(plate.getImageUrl());
        response.setCategoryName(categoryName);
        response.setRestaurantName(restaurantName);
        response.setActive(plate.getActive());

        return response;
    }

    default List<PlateListResponse> toResponseList(List<Plate> plateList) {
        return plateList.stream().map(this::toResponse).toList();
    }
}
