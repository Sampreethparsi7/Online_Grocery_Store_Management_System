package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.DeliveryExecutive;

public interface DeliveryExecutiveRepository extends JpaRepository<DeliveryExecutive, Long> {
	List<DeliveryExecutive>findByAvailabilityStatus(String availabilityStatus);

}
