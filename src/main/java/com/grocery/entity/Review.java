package com.grocery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long reviewId;

	    @ManyToOne
	    @JoinColumn(name = "customer_id", nullable = false)
	    private Customer customer;

	    @ManyToOne
	    @JoinColumn(name = "product_id", nullable = false)
	    private Product product;
	    @Column(nullable = false)
	    private int rating;
	    
	    @Column(nullable = false)
	    private String comment;

	    @Column(nullable = false)
	    private LocalDateTime createdDate;

	    public Review() {
	        this.createdDate = LocalDateTime.now();
	    }

		public Review(Customer customer, Product product, int rating, String comment) {
	
			this.customer = customer;
			this.product = product;
			this.rating = rating;
			this.comment = comment;
			this.createdDate = LocalDateTime.now();
		}

		public Long getReviewId() {
			return reviewId;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

	    
	    

}
