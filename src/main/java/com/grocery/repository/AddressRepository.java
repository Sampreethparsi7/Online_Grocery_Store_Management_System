package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByCustomerCustomerId(Long customerId);

}
