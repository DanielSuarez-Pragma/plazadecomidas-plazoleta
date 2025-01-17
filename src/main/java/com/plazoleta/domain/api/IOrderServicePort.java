package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(Order order, List<OrderPlate> orderPlateList);
    Order getOrderByclientId(Long clientId, String status);

    List<Order> getAllPlates(Long restaurantId, String status, Integer size, Integer page);

    void takeOrder(Long id);

    void notifyOrder(Long id);
}
