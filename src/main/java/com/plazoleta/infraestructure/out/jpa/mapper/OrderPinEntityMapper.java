package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.OrderPin;
import com.plazoleta.infraestructure.out.jpa.entity.OrderPinEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderPinEntityMapper {

    default List<OrderPin> toPlateList(List<OrderPinEntity> orderPinEntities){
        return orderPinEntities.stream().map(orderPinEntity -> {
            OrderPin orderPin = new OrderPin();
            orderPin.setId(orderPinEntity.getId());
            orderPin.setOrderId(orderPinEntity.getOrderId());
            orderPin.setPin(orderPinEntity.getPin());
            return orderPin;
        }).toList();

    }

    default OrderPinEntity toOrderPinEntity(OrderPin orderPin){
        if(orderPin == null) return null;
        OrderPinEntity orderPinEntity = new OrderPinEntity();
        orderPinEntity.setId(orderPin.getId());
        orderPinEntity.setOrderId(orderPin.getOrderId());
        orderPinEntity.setPin(orderPin.getPin());
        return orderPinEntity;
    }
}
