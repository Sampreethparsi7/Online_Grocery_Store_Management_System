package com.grocery.service;

import java.util.List;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;

public interface CartService {
	
	Cart getCartByCustomerId(Long customerId);
    CartItem addItemToCart(Long customerId, Long productId, int quantity);
    CartItem updateCartItem(Long cartItemId, int quantity);
    void removeCartItem(Long cartItemId);
    List<CartItem> getCartItems(Long customerId);

}
