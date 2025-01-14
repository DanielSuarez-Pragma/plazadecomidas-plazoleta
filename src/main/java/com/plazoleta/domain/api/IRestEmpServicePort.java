package com.plazoleta.domain.api;

import com.plazoleta.domain.model.RestaurantEmployee;

import java.util.List;

public interface IRestEmpServicePort {

    void saveRestEmp(RestaurantEmployee restaurantEmployee, List<Long> employeeIds);
    void deleteRestEmp(RestaurantEmployee restaurantEmployee, List<Long> employeeIds);
    RestaurantEmployee findByRestaurantIdAndEmployeeId(Long restaurantId, Long employeeId);

}
