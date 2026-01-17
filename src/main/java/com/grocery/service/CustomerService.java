package com.grocery.service;

import java.util.List;

import com.grocery.entity.Customer;

public interface CustomerService {
	Customer getCustomerByUserId(Long userId);
	Customer createCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerById(Long customerId);

}
