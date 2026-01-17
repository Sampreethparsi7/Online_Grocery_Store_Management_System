package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.StoreOwner;

public interface StoreOwnerRepository extends JpaRepository<StoreOwner, Long> {

}
