package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.OrderPin;
import com.plazoleta.domain.spi.IOrderPinPersistencePort;
import com.plazoleta.infraestructure.out.jpa.entity.OrderPinEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.OrderPinEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.IOrderPinRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderPinJpaAdapter implements IOrderPinPersistencePort {

    private IOrderPinRepository orderPinRepository;
    private OrderPinEntityMapper orderPinEntityMapper;


    @Override
    public List<OrderPin> getAllOrderPin() {
        List<OrderPinEntity> orderPinEntities = orderPinRepository.findAll();
        return orderPinEntityMapper.toPlateList(orderPinEntities);
    }

    @Override
    public void saveOrderPin(OrderPin orderPin) {
        orderPinRepository.save(orderPinEntityMapper.toOrderPinEntity(orderPin));
    }

    @Override
    public void deleteOrderPin(OrderPin orderPin) {
        orderPinRepository.delete(orderPinEntityMapper.toOrderPinEntity(orderPin));
    }

    @Override
    public OrderPin getOrderPinByOrderIdAndPin(Long id, String pin) {
        return orderPinEntityMapper.toOrderPin(orderPinRepository.findByOrderIdAndPin(id,pin));
    }
}
