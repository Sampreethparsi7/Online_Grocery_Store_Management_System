package com.grocery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Customer;
import com.grocery.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
    	return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/me")
    public ResponseEntity<Customer> getMyCustomerProfile(@RequestParam Long userId){
    	Customer customer = customerService.getCustomerByUserId(userId);
    	return ResponseEntity.ok(customer);
    }

}
