package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.infraestructure.out.jpa.entity.RestEmpEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestEmpEntityMapper {


    default RestEmpEntity toEntity(RestaurantEmployee restaurantEmployee){
        RestEmpEntity restEmpEntity = new RestEmpEntity();
        restEmpEntity.setId(restaurantEmployee.getId());
        restEmpEntity.setEmployeeId(restaurantEmployee.getEmployeeId());
        restEmpEntity.setRestaurantId(restaurantEmployee.getRestaurantId());

        return restEmpEntity;
    }

    default RestaurantEmployee toRestEmp(RestEmpEntity restEmpEntity){

        if (restEmpEntity == null){
            return null;
        }

        RestaurantEmployee restaurantEmployee = new RestaurantEmployee();
        restaurantEmployee.setId(restEmpEntity.getId());
        restaurantEmployee.setEmployeeId(restEmpEntity.getEmployeeId());
        restaurantEmployee.setRestaurantId(restEmpEntity.getRestaurantId());
        return restaurantEmployee;
    }
}
