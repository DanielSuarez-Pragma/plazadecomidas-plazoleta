package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infraestructure.out.jpa.mapper.OrderEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }

    @Override
    public Order getOrderByClientId(Long id, String status) {
        return orderEntityMapper.toOrder(orderRepository.findByClientIdAndStatus(id, status).orElse(null));
    }
}
