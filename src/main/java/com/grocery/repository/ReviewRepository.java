package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	
	List<Review> findByProductProductId(Long productId);
	

}
