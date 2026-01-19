package com.grocery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.grocery.entity.User;
import com.grocery.exception.DuplicateResourceException;
import com.grocery.service.AuthService;

@SpringBootTest

@Transactional

class AuthServiceTest {

    @Autowired

    private AuthService authService;

    // Register user - SUCCESS

    @Test

    void registerUser_success() {

        User user = new User();

        user.setName("Test User");

        user.setEmail("testuser@gmail.com");

        user.setPassword("12345");

        user.setPhone("9999999999");

        user.setRole("CUSTOMER");

        User savedUser = authService.register(user);

        assertNotNull(savedUser.getUserId());

        assertEquals("testuser@gmail.com", savedUser.getEmail());

        assertEquals("ACTIVE", savedUser.getStatus());

        assertEquals("CUSTOMER", savedUser.getRole());

    }

    // Register user - DUPLICATE EMAIL

    @Test

    void registerUser_duplicateEmail_shouldThrowException() {

        User user1 = new User();

        user1.setName("User One");

        user1.setEmail("duplicate@gmail.com");

        user1.setPassword("12345");

        user1.setPhone("8888888888");

        user1.setRole("CUSTOMER");

        authService.register(user1);

        User user2 = new User();

        user2.setName("User Two");

        user2.setEmail("duplicate@gmail.com");

        user2.setPassword("12345");

        user2.setPhone("7777777777");

        user2.setRole("CUSTOMER");

        assertThrows(DuplicateResourceException.class, () -> {

            authService.register(user2);

        });

    }

}