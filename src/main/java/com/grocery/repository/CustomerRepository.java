package com.grocery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByUser_UserId(Long userId);

}
