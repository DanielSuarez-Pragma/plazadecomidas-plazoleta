package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.OrderRequest;

public interface IOrderListHandler {
    void saveOrderInList(OrderRequest orderRequest);
}
