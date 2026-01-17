package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Customer;
import com.grocery.entity.Product;
import com.grocery.entity.Wishlist;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.ProductRepository;
import com.grocery.repository.WishlistRepository;

@Service
public class WishlistServiceImpl implements WishlistService{
	 private final WishlistRepository wishlistRepository;
	    private final CustomerRepository customerRepository;
	    private final ProductRepository productRepository;
	    public WishlistServiceImpl(WishlistRepository wishlistRepository,
	                               CustomerRepository customerRepository,
	                               ProductRepository productRepository) {
	        this.wishlistRepository = wishlistRepository;
	        this.customerRepository = customerRepository;
	        this.productRepository = productRepository;
	    }

	    @Override
	    public Wishlist addToWishlist(Long customerId, Long productId) {
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	        Wishlist wishlist = new Wishlist(customer, product);
	        return wishlistRepository.save(wishlist);
	    }

	    @Override
	    public void removeFromWishlist(Long wishlistId) {
	        Wishlist wishlist = wishlistRepository.findById(wishlistId)
	                .orElseThrow(() -> new ResourceNotFoundException("Wishlist item not found"));
	        wishlistRepository.delete(wishlist);
	    }

	    @Override
	    public List<Wishlist> getWishlistByCustomer(Long customerId) {
	        return wishlistRepository.findByCustomerCustomerId(customerId);
	    }
	

}
