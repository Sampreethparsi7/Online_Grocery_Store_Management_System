package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Customer;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerByUserId(Long userId) {
		// TODO Auto-generated method stub
		return customerRepository.findByUser_UserId(userId).orElseThrow(()-> new ResourceNotFoundException("Customer not found for userId: " +userId));
	}
	

}
