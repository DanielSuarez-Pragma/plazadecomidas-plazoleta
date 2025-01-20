package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.dto.request.TraceClientResponse;
import com.plazoleta.application.dto.response.CancelResponse;
import com.plazoleta.application.dto.response.OrderResponse;
import com.plazoleta.application.mapper.request.OrderListRequestMapper;
import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPlate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class OrderListHandler implements IOrderListHandler{

    private final IOrderServicePort orderServicePort;
    private final OrderListRequestMapper orderListRequestMapper;

    @Override
    public void saveOrderInList(OrderRequest orderRequest) {
        Order order = orderListRequestMapper.toOrder(orderRequest);
        List<OrderPlate> orderPlateList = orderListRequestMapper.toOrderPlateList(orderRequest.getPlates());
        orderServicePort.saveOrder(order, orderPlateList);
    }

    @Override
    public List<OrderResponse> getAllPlatesFromList(Long restaurantId, String status, Integer size, Integer page) {
        return orderListRequestMapper.toResponseList(orderServicePort.getAllPlates(restaurantId, status, size, page));
    }

    @Override
    public void takeOrder(Long id) {

        orderServicePort.takeOrder(id);
    }

    @Override
    public void notifyOrder(Long id) {
        orderServicePort.notifyOrder(id);
    }

    @Override
    public void deliverOrder(Long orderID, String pin) {
        orderServicePort.deliverOrder(orderID, pin);
    }

    @Override
    public CancelResponse cancelOrder(Long id) {
        return orderListRequestMapper.toCancelResponse(orderServicePort.cancelOrder(id));
    }

    @Override
    public List<TraceClientResponse> traceClient() {
        return orderListRequestMapper.toResponseTrace(orderServicePort.getTraceClient());
    }

    @Override
    public List<TraceClientResponse> traceRestaurant(Long restaurantId) {
        return orderListRequestMapper.toResponseTrace(orderServicePort.getTraceRestaurant(restaurantId));
    }
}
