package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.domain.spi.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IPlatePersistencePort platePersistencePort;
    private final IOrderPlatePersistencePort orderPlatePersistencePort;
    private final IRestEmpPersistencePort restEmpPersistencePort;

    @Override
    public void saveOrder(Order order, List<OrderPlate> orderPlateList) {

        if(restaurantPersistencePort.getRestaurant(order.getRestaurantId()) == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        if (getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), "Pendiente") != null  ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), "Preparacion") != null ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), "Listo") != null){
                throw new IllegalArgumentException("Order already exists");
            }

        orderPlateList.forEach(orderPlate -> {
            if (!Objects.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getRestaurantId(), order.getRestaurantId())) {
                throw new IllegalArgumentException("Restaurant id mismatch");
            }
            if (Boolean.FALSE.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getActive())){
                throw new IllegalArgumentException("Plate is not active");
            }
        });

        order.setClientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));

        orderPersistencePort.saveOrder(order);

        Order orderSaved = getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()),"Pendiente");

        orderPlateList.forEach(orderPlate -> {
            orderPlate.setOrderId(orderSaved.getId());
            orderPlatePersistencePort.saveOrderPlate(orderPlate);
        });

    }

    @Override
    public Order getOrderByclientId(Long clientId, String status) {
        return orderPersistencePort.getOrderByClientId(clientId, status);
    }

    @Override
    public List<Order> getAllPlates(Long restaurantId, String status, Integer size, Integer page) {
        if(restaurantPersistencePort.getRestaurant(restaurantId) == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        if (restEmpPersistencePort.findByRestaurantIdAndEmployeeId(restaurantId, userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId())) == null) {
            throw new IllegalArgumentException("You dont have permission to access this resource");
        }

        return orderPersistencePort.findByRestaurantIdAndStatus(restaurantId, status, size, page);
    }
}
