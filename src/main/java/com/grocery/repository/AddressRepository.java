package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
