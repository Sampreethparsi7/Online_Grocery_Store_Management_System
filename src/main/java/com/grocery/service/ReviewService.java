package com.grocery.service;

import java.util.List;

import com.grocery.entity.Review;

public interface ReviewService {
	
	 Review addReview(Long customerId, Long productId, int rating, String comment);
	    List<Review> getReviewsByProduct(Long productId);

}
