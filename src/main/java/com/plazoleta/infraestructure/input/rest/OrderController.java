package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.handler.IOrderListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class OrderController {

    private final IOrderListHandler orderListHandler;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<Void> saveOrder(@RequestBody OrderRequest orderRequest) {
        orderListHandler.saveOrderInList(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
