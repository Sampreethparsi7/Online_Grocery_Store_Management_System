package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
	List<Wishlist> findByCustomerCustomerId(Long customerId);
	

}
