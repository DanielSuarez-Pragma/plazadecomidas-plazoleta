package com.plazoleta.infraestructure.input.rest;

import com.plazoleta.application.dto.request.OrderRequest;
import com.plazoleta.application.dto.response.OrderResponse;
import com.plazoleta.application.handler.IOrderListHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@PreAuthorize("denyAll()")
public class OrderController {

    private final IOrderListHandler orderListHandler;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('CLIENTE')")
    public ResponseEntity<Void> saveOrder(@RequestBody OrderRequest orderRequest) {
        orderListHandler.saveOrderInList(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/getOrders/restaurant/{restaurantId}/status/{status}/size/{size}/page/{page}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<List<OrderResponse>> getAllPlaterFromList(@PathVariable Long restaurantId, @PathVariable String status, @PathVariable Integer size, @PathVariable Integer page){
        return ResponseEntity.ok(orderListHandler.getAllPlatesFromList(restaurantId,status,size,page));
    }

    @PutMapping("/takeOrder/{id}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> takeOrder(@PathVariable Long id){
        orderListHandler.takeOrder(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/notify/{id}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> notifyOrder(@PathVariable Long id){
        orderListHandler.notifyOrder(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/deliver/order/{orderID}/pin/{pin}")
    @PreAuthorize("hasAuthority('EMPLEADO')")
    public ResponseEntity<Void> deliverOrder(@PathVariable Long orderID, @PathVariable String pin){
        orderListHandler.deliverOrder(orderID, pin);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
