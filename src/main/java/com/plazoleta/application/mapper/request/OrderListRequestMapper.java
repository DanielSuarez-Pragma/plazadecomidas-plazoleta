package com.plazoleta.application.mapper.request;

import com.plazoleta.application.dto.request.OrderPlateRequest;
import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.dto.request.TraceClientResponse;
import com.plazoleta.application.dto.response.CancelResponse;
import com.plazoleta.application.dto.response.OrderResponse;
import com.plazoleta.domain.model.CancelMessage;
import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.domain.model.TraceClient;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderListRequestMapper {

    default Order toOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setDate(null);
        order.setStatus(null);
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

    default OrderResponse toResponse(Order order) {
        if(order == null) {
            return null;
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setClientId(order.getClientId());
        orderResponse.setDate(order.getDate());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setChefId(order.getChefId());
        orderResponse.setRestaurantId(order.getRestaurantId());
        return orderResponse;
    }

    default List<OrderResponse> toResponseList(List<Order> orderPlateList) {
        return orderPlateList.stream().map(this::toResponse).toList();
    }

    default CancelResponse toCancelResponse(CancelMessage cancelMessage) {
        CancelResponse cancelResponse = new CancelResponse();
        cancelResponse.setMessage(cancelMessage.getMessage());
        return cancelResponse;
    }

    default List<TraceClientResponse> toResponseTrace(List<TraceClient> traceClient){
        return traceClient.stream().map(trace ->{
            TraceClientResponse traceClientResponse = new TraceClientResponse();
            traceClientResponse.setOrderId( trace.getOrderId() );
            traceClientResponse.setClientEmail( trace.getClientEmail() );
            traceClientResponse.setDate( trace.getDate() );
            traceClientResponse.setLastState( trace.getLastState() );
            traceClientResponse.setNewState( trace.getNewState() );
            traceClientResponse.setEmployeeEmail( trace.getEmployeeEmail() );
            return traceClientResponse;

        }).toList();
    }
}
