package com.parfum.controller;

import com.parfum.dto.OrderDTO.*;
import com.parfum.model.Order;
import com.parfum.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest request,
                                             Authentication auth) {
        return ResponseEntity.ok(orderService.createOrder(request, auth.getName()));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getMyOrders(Authentication auth) {
        return ResponseEntity.ok(orderService.getUserOrders(auth.getName()));
    }

    @PostMapping("/payment")
    public ResponseEntity<Order> processPayment(@RequestBody PaymentRequest request) {
        return ResponseEntity.ok(orderService.processPayment(request));
    }
}