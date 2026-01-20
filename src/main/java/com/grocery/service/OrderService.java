package com.grocery.service;

import java.util.List;

import com.grocery.entity.Order;

public interface OrderService {
	Order placeOrder(Long customerId);
    Order getOrderById(Long orderId);
    List<Order> getOrdersByCustomer(Long customerId);
    Order updateOrderStatus(Long orderId, String status);
    List<Order> getAllOrders();

}
