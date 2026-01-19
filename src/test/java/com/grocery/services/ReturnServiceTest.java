package com.grocery.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocery.service.ReturnService;

import jakarta.transaction.Transactional;

@SpringBootTest

@Transactional

class ReturnServiceTest {

    @Autowired

    private ReturnService returnService;

    @Test

    void returnServiceShouldLoad() {

        assertNotNull(returnService);

    }

    @Test

    void requestReturn_shouldThrowException_forInvalidOrder() {

        assertThrows(RuntimeException.class, () -> {

            returnService.requestReturn(999L, "damaged");

        });

    }

}