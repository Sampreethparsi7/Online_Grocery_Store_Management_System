package com.grocery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Order;
import com.grocery.mapper.OrderMapper;
import com.grocery.response.OrderResponse;
import com.grocery.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService,
                           OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestParam Long customerId) {
        Order order = orderService.placeOrder(customerId);
        return ResponseEntity.ok(orderMapper.toOrderResponse(order));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomer(
            @PathVariable Long customerId) {

        List<OrderResponse> responses =
                orderService.getOrdersByCustomer(customerId)
                            .stream()
                            .map(orderMapper::toOrderResponse)
                            .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orderMapper.toOrderResponse(order));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        List<OrderResponse> responses =
                orderService.getAllOrders()
                        .stream()
                        .map(orderMapper::toOrderResponse)
                        .toList();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update-status")
    public ResponseEntity<OrderResponse> updateOrderStatus(
            @RequestParam Long orderId,
            @RequestParam String status) {

        Order order = orderService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok(orderMapper.toOrderResponse(order));
    }
}