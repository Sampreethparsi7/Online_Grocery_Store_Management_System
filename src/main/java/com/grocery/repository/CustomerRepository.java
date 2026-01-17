package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
