package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.domain.spi.IOrderPlatePersistencePort;
import com.plazoleta.infraestructure.out.jpa.mapper.OrderPlateEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IOrderPlateRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderPlateJpaAdapter implements IOrderPlatePersistencePort {

    private final IOrderPlateRepository orderPlateRepository;
    private final OrderPlateEntityMapper orderPlateEntityMapper;

    @Override
    public void saveOrderPlate(OrderPlate orderPlate) {
        orderPlateRepository.save(orderPlateEntityMapper.toEntity(orderPlate));
    }
}
