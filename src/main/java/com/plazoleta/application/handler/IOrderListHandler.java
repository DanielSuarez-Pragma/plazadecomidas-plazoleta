package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.dto.request.TraceClientResponse;
import com.plazoleta.application.dto.response.CancelResponse;
import com.plazoleta.application.dto.response.OrderResponse;

import java.util.List;

public interface IOrderListHandler {
    void saveOrderInList(OrderRequest orderRequest);

    List<OrderResponse> getAllPlatesFromList(Long restaurantId, String status, Integer size, Integer page);

    void takeOrder(Long id);

    void notifyOrder(Long id);

    void deliverOrder(Long orderID, String pin);

    CancelResponse cancelOrder(Long id);

    List<TraceClientResponse> traceClient();

    List<TraceClientResponse> traceRestaurant(Long id);
}

