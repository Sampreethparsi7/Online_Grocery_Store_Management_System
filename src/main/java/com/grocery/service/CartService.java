package com.grocery.service;

import java.util.List;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;

public interface CartService {
	
	Cart getCartByCustomerId(Long customerId);
	List<CartItem> getCartItems(Long customerId);
    Cart addToCart(Long customerId, Long productId, int quantity);
    Cart updateCartItem(Long cartItemId,Long productId, int quantity);
    Cart removeFromCart(Long cartItemId,Long productId);

}
