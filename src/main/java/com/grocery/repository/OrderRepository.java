package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByCustomerCustomerId(Long customerId);
	List<Order> findByStatus(String status);
	

}
