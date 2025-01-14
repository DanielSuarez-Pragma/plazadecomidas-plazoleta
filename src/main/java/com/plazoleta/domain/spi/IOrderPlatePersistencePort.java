package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.OrderPlate;

public interface IOrderPlatePersistencePort {
    void saveOrderPlate(OrderPlate orderPlate);
}
