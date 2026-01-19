package com.grocery.service;

import org.springframework.stereotype.Service;

import com.grocery.entity.Customer;

import com.grocery.entity.DeliveryExecutive;

import com.grocery.entity.User;

import com.grocery.exception.DuplicateResourceException;

import com.grocery.exception.ResourceNotFoundException;

import com.grocery.repository.CustomerRepository;

import com.grocery.repository.DeliveryExecutiveRepository;

import com.grocery.repository.UserRepository;

@Service

public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    public AuthServiceImpl(UserRepository userRepository,

                           CustomerRepository customerRepository,

                           DeliveryExecutiveRepository deliveryExecutiveRepository) {

        this.userRepository = userRepository;

        this.customerRepository = customerRepository;

        this.deliveryExecutiveRepository = deliveryExecutiveRepository;

    }

    @Override

    public User register(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {

            throw new DuplicateResourceException("Email already exists");

        }

        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        // Auto-create CUSTOMER

        if ("CUSTOMER".equalsIgnoreCase(savedUser.getRole())) {

            Customer customer = new Customer(savedUser);

            customerRepository.save(customer);

        }

        // Auto-create DELIVERY EXECUTIVE

        if ("DELIVERY_EXECUTIVE".equalsIgnoreCase(savedUser.getRole())) {

            DeliveryExecutive executive =

                    new DeliveryExecutive(savedUser, "AVAILABLE");

            deliveryExecutiveRepository.save(executive);

        }

        return savedUser;

    }

    @Override

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)

                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!user.getPassword().equals(password)) {

            throw new RuntimeException("Invalid credentials");

        }

        return user;

    }

    @Override

    public void resetPassword(String email, String newPassword) {

        User user = userRepository.findByEmail(email)

                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setPassword(newPassword);

        userRepository.save(user);

    }

}