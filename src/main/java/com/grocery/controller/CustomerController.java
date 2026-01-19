package com.grocery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Customer;
import com.grocery.mapper.CustomerMapper;
import com.grocery.response.CustomerResponse;
import com.grocery.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	private final CustomerService customerService;

    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService,

                              CustomerMapper customerMapper) {

        this.customerService = customerService;

        this.customerMapper = customerMapper;

    }

    @GetMapping

    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        List<CustomerResponse> responses = customerService.getAllCustomers()

                .stream()

                .map(customerMapper::toCustomerResponse)

                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);

    }

    @GetMapping("/{customerId}")

    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId) {

        Customer customer = customerService.getCustomerById(customerId);

        return ResponseEntity.ok(customerMapper.toCustomerResponse(customer));

    }

}
