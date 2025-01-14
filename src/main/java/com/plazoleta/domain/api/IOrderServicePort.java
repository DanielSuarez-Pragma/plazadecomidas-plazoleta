package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;

import java.util.List;

public interface IOrderServicePort {
    void saveOrder(Order order, List<OrderPlate> orderPlateList);
    Order getOrderByclientId(Long clientId, String status);
}
