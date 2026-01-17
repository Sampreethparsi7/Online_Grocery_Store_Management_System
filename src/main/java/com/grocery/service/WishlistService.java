package com.grocery.service;

import java.util.List;

import com.grocery.entity.Wishlist;

public interface WishlistService {
	Wishlist addToWishlist(Long customerId, Long productId);
    void removeFromWishlist(Long wishlistId);
    List<Wishlist> getWishlistByCustomer(Long customerId);

}
