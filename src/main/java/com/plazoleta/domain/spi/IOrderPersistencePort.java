package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    Order getOrderByClientId(Long id, String status);

    List<Order> findByRestaurantIdAndStatus(Long restaurantId, String status, Integer size, Integer page);
}
