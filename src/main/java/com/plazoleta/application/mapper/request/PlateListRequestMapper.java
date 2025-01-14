package com.plazoleta.application.mapper.request;

import com.plazoleta.application.dto.request.PlateListRequest;
import com.plazoleta.domain.model.Plate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlateListRequestMapper {

    // Método personalizado para mapear PlateListRequest a Plate
    default Plate toPlate(PlateListRequest request) {
        Plate plate = new Plate();
        plate.setName(request.getName());
        plate.setDescription(request.getDescription());
        plate.setPrice(request.getPrice());
        plate.setImageUrl(request.getImageUrl());
        plate.setActive(true); // Por defecto activo

        // Asignaciones manuales para IDs
        if (request.getCategoryId() != null) {
            plate.setCategoryId(request.getCategoryId());
        }
        if (request.getRestaurantId() != null) {
            plate.setRestaurantId(request.getRestaurantId());
        }
        return plate;
    }

    // Customized mapping to handle nested objects if needed
    default Plate mapToPlateWithDefaults(PlateListRequest request) {
        Plate plate = new Plate();
        plate.setName(request.getName());
        plate.setDescription(request.getDescription());
        plate.setPrice(request.getPrice());
        plate.setImageUrl(request.getImageUrl());
        plate.setActive(true); // Default to active

        // Category and Restaurant associations
        if (request.getCategoryId() != null) {
            plate.setCategoryId(request.getCategoryId());
        }
        if (request.getRestaurantId() != null) {
            plate.setRestaurantId(request.getRestaurantId());
        }
        return plate;
    }
}
