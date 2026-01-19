package com.grocery.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.grocery.entity.DeliveryExecutive;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.DeliveryExecutiveRepository;

@Service
public class DeliveryExecutiveServiceImpl implements DeliveryExecutiveService {

    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
    private final Random random = new Random();

    public DeliveryExecutiveServiceImpl(DeliveryExecutiveRepository deliveryExecutiveRepository) {
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
    }

    @Override
    public DeliveryExecutive assignAvailableExecutive() {

        List<DeliveryExecutive> availableExecutives =
                deliveryExecutiveRepository.findByAvailabilityStatus("AVAILABLE");

        if (availableExecutives.isEmpty()) {
            throw new ResourceNotFoundException("No delivery executive available");
        }

        DeliveryExecutive executive =
                availableExecutives.get(random.nextInt(availableExecutives.size()));

        executive.setAvailabilityStatus("BUSY");
        return deliveryExecutiveRepository.save(executive);
    }

    @Override
    public void markExecutiveAvailable(Long deliveryExecutiveId) {

        DeliveryExecutive executive = deliveryExecutiveRepository.findById(deliveryExecutiveId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery executive not found"));

        executive.setAvailabilityStatus("AVAILABLE");
        deliveryExecutiveRepository.save(executive);
    }
}