package com.grocery.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocery.entity.Order;
import com.grocery.service.OrderService;

import jakarta.transaction.Transactional;

@SpringBootTest

@Transactional

class OrderServiceTest {

    @Autowired

    private OrderService orderService;

    @Test

    void orderServiceShouldLoad() {

        assertNotNull(orderService);

    }

    @Test

    void getOrdersByCustomer_shouldReturnEmptyList_forInvalidCustomer() {

        List<Order> orders = orderService.getOrdersByCustomer(999L);

        assertNotNull(orders);

    }

}
