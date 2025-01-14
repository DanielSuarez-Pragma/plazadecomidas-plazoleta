package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Order;

public interface IOrderPersistencePort {
    void saveOrder(Order order);
    Order getOrderByClientId(Long id, String status);
}
