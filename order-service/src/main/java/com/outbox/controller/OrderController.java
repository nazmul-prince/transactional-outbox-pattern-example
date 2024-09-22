package com.outbox.controller;

import com.outbox.common.dto.OrderRequestDTO;
import com.outbox.order.entity.Order;
import com.outbox.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        Order createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer id, @RequestBody OrderRequestDTO orderRequest) {
        Order createdOrder = orderService.updateOrder(id, orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
