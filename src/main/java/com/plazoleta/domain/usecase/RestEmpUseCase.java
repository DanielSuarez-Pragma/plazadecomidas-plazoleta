package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IRestEmpServicePort;
import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.model.RestaurantEmployee;
import com.plazoleta.domain.spi.IRestEmpPersistencePort;
import com.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.plazoleta.domain.spi.IUserPersistencePort;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.plazoleta.domain.constants.ErrorRestConstants.*;

@AllArgsConstructor
public class RestEmpUseCase implements IRestEmpServicePort {

    private final IRestEmpPersistencePort restEmpPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveRestEmp(RestaurantEmployee restaurantEmployee, List<Long> employeeIds) {
        if (restaurantPersistencePort.getRestaurant(restaurantEmployee.getRestaurantId()) == null) {
            throw new InvalidErrorException(NO_REST_FOUNDS);
        }
        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(restaurantEmployee.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }

        employeeIds.forEach(employee->{
            if(userPersistencePort.getUserById(employee) != 2) {
                throw new IllegalArgumentException("The user "+employee+" is not a employee.");
            }

            if (findByRestaurantIdAndEmployeeId(restaurantEmployee.getRestaurantId(), employee) != null) {
                throw new InvalidErrorException(PERMISSION_ALREADY_EXIST);
            }

        });

        employeeIds.forEach(employeeId -> {
            restaurantEmployee.setEmployeeId(employeeId);
            restEmpPersistencePort.saveRestEmp(restaurantEmployee);
        });
    }

    @Override
    public void deleteRestEmp(RestaurantEmployee restaurantEmployee, List<Long> employeeIds) {
        if (restaurantPersistencePort.getRestaurant(restaurantEmployee.getRestaurantId()) == null) {
            throw new InvalidErrorException(NO_REST_FOUNDS);
        }
        long authOwner= userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId());
        long restOwner= restaurantPersistencePort.getRestaurant(restaurantEmployee.getRestaurantId()).getOwnerId();
        if (authOwner != restOwner) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        employeeIds.forEach(employeeId -> {
            restaurantEmployee.setEmployeeId(employeeId);
            restEmpPersistencePort.deleteRestEmp(restaurantEmployee);
        });
    }

    @Override
    public RestaurantEmployee findByRestaurantIdAndEmployeeId(Long restaurantId, Long employeeId) {
        return restEmpPersistencePort.findByRestaurantIdAndEmployeeId(restaurantId, employeeId);
    }
}