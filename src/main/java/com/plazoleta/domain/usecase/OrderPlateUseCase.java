package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IOrderPlateServicePort;
import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.domain.spi.IOrderPlatePersistencePort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderPlateUseCase implements IOrderPlateServicePort {

    private final IOrderPlatePersistencePort orderPlatePersistencePort;

    @Override
    public void saveOrderPlate(OrderPlate orderPlate) {
        orderPlatePersistencePort.saveOrderPlate(orderPlate);
    }
}
