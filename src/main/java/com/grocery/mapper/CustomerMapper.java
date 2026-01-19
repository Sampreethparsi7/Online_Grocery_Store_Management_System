package com.grocery.mapper;

import org.springframework.stereotype.Component;

import com.grocery.entity.Customer;
import com.grocery.response.CustomerResponse;

@Component
public class CustomerMapper {
	 public CustomerResponse toCustomerResponse(Customer customer) {
	        return new CustomerResponse(
	                customer.getCustomerId(),
	                customer.getUser().getUserId(),
	                customer.getUser().getName(),
	                customer.getUser().getEmail(),
	                customer.getUser().getPhone()
	        );
	    }

}
