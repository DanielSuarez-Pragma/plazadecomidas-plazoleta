package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.spi.IOrderPersistencePort;
import com.plazoleta.infraestructure.exception.NoDataFoundException;
import com.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.OrderEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

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

    @Override
    public List<Order> findByRestaurantIdAndStatus(Long restaurantId, String status, Integer size, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderEntity> orderEntities = orderRepository.findByRestaurantIdAndStatus(restaurantId, status, pageRequest);

        if (orderEntities.isEmpty()){
            throw new NoDataFoundException();
        }
        return orderEntities.getContent()
                .stream()
                .map(orderEntityMapper::toOrder)
                .toList();
    }

    @Override
    public Order findById(Long id) {
        return orderEntityMapper.toOrder(orderRepository.findById(id).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public void takeOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
