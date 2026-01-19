package com.grocery.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.grocery.entity.DeliveryExecutive;
import com.grocery.repository.DeliveryExecutiveRepository;

import jakarta.transaction.Transactional;

@SpringBootTest

@Transactional

class DeliveryExecutiveRepositoryTest {

    @Autowired

    private DeliveryExecutiveRepository repository;

    @Test

    void findAvailableExecutives_shouldNotFail() {

        List<DeliveryExecutive> list =

                repository.findByAvailabilityStatus("AVAILABLE");

        assertNotNull(list);

    }

}
