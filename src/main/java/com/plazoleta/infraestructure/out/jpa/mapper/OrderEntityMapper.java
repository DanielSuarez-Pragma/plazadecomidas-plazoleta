package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.Order;
import com.plazoleta.infraestructure.out.jpa.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderEntityMapper {
    default OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(order.getId());
        orderEntity.setClientId(order.getClientId());
        orderEntity.setDate(order.getDate());
        orderEntity.setStatus(order.getStatus());
        orderEntity.setChefId(order.getChefId());
        orderEntity.setRestaurantId(order.getRestaurantId());
        return orderEntity;
    }

    default Order toOrder(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        Order order = new Order();
        order.setId(entity.getId());
        order.setClientId(entity.getClientId());
        order.setDate(entity.getDate());
        order.setStatus(entity.getStatus());
        order.setChefId(entity.getChefId());
        order.setRestaurantId(entity.getRestaurantId());
        return order;
    }
}
