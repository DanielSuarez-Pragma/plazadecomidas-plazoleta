package com.plazoleta.application.mapper.request;

import com.plazoleta.application.dto.request.OrderPlateRequest;
import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderListRequestMapper {

    default Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setDate(new Date());
        order.setStatus("Pendiente");
        if (orderRequest.getRestaurantId() != null) {
            order.setRestaurantId(orderRequest.getRestaurantId());
        }
        return order;
    }

    default List<OrderPlate> toOrderPlateList(List<OrderPlateRequest> plateRequests) {
        if (plateRequests == null || plateRequests.isEmpty()) {
            return Collections.emptyList();
        }

        return plateRequests.stream().map(plateRequest -> {
            OrderPlate orderPlate = new OrderPlate();
            orderPlate.setPlateId(plateRequest.getPlateId());
            orderPlate.setQuantity(plateRequest.getQuantity());
            return orderPlate;
        }).toList();
    }
}
