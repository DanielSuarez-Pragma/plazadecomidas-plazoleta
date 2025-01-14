package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestEmpRequest;
import com.plazoleta.application.mapper.request.RestEmpRequestMapper;
import com.plazoleta.domain.api.IRestEmpServicePort;
import com.plazoleta.domain.model.RestaurantEmployee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RestEmpHandler implements IRestEmpHandler{

    private final IRestEmpServicePort restEmpServicePort;
    private final RestEmpRequestMapper restEmpRequestMapper;

    @Override
    public void saveRestEmp(RestEmpRequest restEmpRequest) {
        RestaurantEmployee restaurantEmployee = restEmpRequestMapper.toRestaurantEmployee(restEmpRequest);
        restEmpServicePort.saveRestEmp(restaurantEmployee, restEmpRequest.getEmployeeIds());
    }

    @Override
    public void deleteRestEmp(RestEmpRequest restEmpRequest) {
        RestaurantEmployee restaurantEmployee = restEmpRequestMapper.toRestaurantEmployee(restEmpRequest);
        restEmpServicePort.deleteRestEmp(restaurantEmployee, restEmpRequest.getEmployeeIds());
    }
}
