package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.dto.response.OrderResponse;

import java.util.List;

public interface IOrderListHandler {
    void saveOrderInList(OrderRequest orderRequest);

    List<OrderResponse> getAllPlatesFromList(Long restaurantId, String status, Integer size, Integer page);

    void takeOrder(Long id);
}

