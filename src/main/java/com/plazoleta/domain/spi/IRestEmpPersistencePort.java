package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.RestaurantEmployee;

public interface IRestEmpPersistencePort {

    void saveRestEmp(RestaurantEmployee restaurantEmployee);
    void deleteRestEmp(RestaurantEmployee restaurantEmployee);
    RestaurantEmployee findByRestaurantIdAndEmployeeId(Long restaurantId, Long employeeId);

}
