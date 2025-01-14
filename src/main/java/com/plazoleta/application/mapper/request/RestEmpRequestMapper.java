package com.plazoleta.application.mapper.request;

import com.plazoleta.application.dto.request.RestEmpRequest;
import com.plazoleta.domain.model.RestaurantEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestEmpRequestMapper {

    default RestaurantEmployee toRestaurantEmployee(final RestEmpRequest restEmpRequest) {
        if (restEmpRequest == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
        }
        RestaurantEmployee restEmployee = new RestaurantEmployee();
        restEmployee.setRestaurantId(restEmpRequest.getRestaurantId());
        return restEmployee;
    }
}
