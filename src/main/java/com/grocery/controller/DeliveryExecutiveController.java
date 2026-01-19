package com.grocery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.DeliveryExecutive;
import com.grocery.entity.User;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.DeliveryExecutiveRepository;
import com.grocery.repository.UserRepository;

@RestController
@RequestMapping("/api/delivery-executives")
public class DeliveryExecutiveController {
	
	private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    private final UserRepository userRepository;

    public DeliveryExecutiveController(DeliveryExecutiveRepository deliveryExecutiveRepository,

                                       UserRepository userRepository) {

        this.deliveryExecutiveRepository = deliveryExecutiveRepository;

        this.userRepository = userRepository;

    }

    @PostMapping("/add")

    public ResponseEntity<DeliveryExecutive> addDeliveryExecutive(

            @RequestParam Long userId) {

        User user = userRepository.findById(userId)

                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        DeliveryExecutive executive = new DeliveryExecutive(user, "AVAILABLE");

        return ResponseEntity.ok(deliveryExecutiveRepository.save(executive));

    }

    @GetMapping

    public ResponseEntity<List<DeliveryExecutive>> getAllExecutives() {

        return ResponseEntity.ok(deliveryExecutiveRepository.findAll());

    }

    @GetMapping("/available")

    public ResponseEntity<List<DeliveryExecutive>> getAvailableExecutives() {

        return ResponseEntity.ok(

                deliveryExecutiveRepository.findByAvailabilityStatus("AVAILABLE"));

    }

}
