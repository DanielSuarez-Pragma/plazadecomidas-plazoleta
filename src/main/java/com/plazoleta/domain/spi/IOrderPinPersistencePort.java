package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.OrderPin;

import java.util.List;

public interface IOrderPinPersistencePort {

    List<OrderPin> getAllOrderPin();

    void saveOrderPin(OrderPin orderPin);
}
