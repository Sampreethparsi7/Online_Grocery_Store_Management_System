package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Customer;
import com.grocery.entity.Product;
import com.grocery.entity.Review;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.ProductRepository;
import com.grocery.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService{
	private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             CustomerRepository customerRepository,
                             ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Review addReview(Long customerId, Long productId, int rating, String comment) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Review review = new Review(customer, product, rating, comment);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewsByProduct(Long productId) {
        return reviewRepository.findByProductProductId(productId);
    }

}
