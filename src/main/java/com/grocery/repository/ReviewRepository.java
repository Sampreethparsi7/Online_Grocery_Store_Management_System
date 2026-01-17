package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.ReturnRequest;

public interface ReviewRepository extends JpaRepository<ReturnRequest, Long> {
	
	
	

}
