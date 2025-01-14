package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.spi.IRestEmpPersistencePort;
import com.plazoleta.infraestructure.out.jpa.mapper.RestEmpEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IRestEmpRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestEmpJpaAdapter implements IRestEmpPersistencePort {

    private final IRestEmpRepository repository;
    private final RestEmpEntityMapper mapper;

    @Override
    public void saveRestEmp(RestaurantEmployee restaurantEmployee) {
        repository.save(mapper.toEntity(restaurantEmployee));
    }

    @Override
    public void deleteRestEmp(RestaurantEmployee restaurantEmployee) {
        repository.delete(mapper.toEntity(restaurantEmployee));
    }

    @Override
    public RestaurantEmployee findByRestaurantIdAndEmployeeId(Long restaurantId, Long employeeId) {
        return mapper.toRestEmp(repository.findByRestaurantIdAndEmployeeId(restaurantId, employeeId).orElse(null));
    }
}
