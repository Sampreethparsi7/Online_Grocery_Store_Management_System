package com.grocery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCartCartId(Long cartId);
	Optional<CartItem>findByCartCartIdAndProductProductId(Long cartId, Long productId);
	

}
