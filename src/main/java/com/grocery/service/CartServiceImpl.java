package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;
import com.grocery.entity.Customer;
import com.grocery.entity.Product;
import com.grocery.exception.InsufficientStockException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CartItemRepository;
import com.grocery.repository.CartRepository;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.ProductRepository;

@Service

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    private final CartItemRepository cartItemRepository;

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository,

                           CartItemRepository cartItemRepository,

                           CustomerRepository customerRepository,

                           ProductRepository productRepository) {

        this.cartRepository = cartRepository;

        this.cartItemRepository = cartItemRepository;

        this.customerRepository = customerRepository;

        this.productRepository = productRepository;

    }

    @Override

    public Cart getCartByCustomerId(Long customerId) {

        return cartRepository.findByCustomerCustomerId(customerId)

                .orElseGet(() -> {

                    Customer customer = customerRepository.findById(customerId)

                            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

                    return cartRepository.save(new Cart(customer));

                });

    }

    @Override

    public Cart addToCart(Long customerId, Long productId, int quantity) {

        Cart cart = getCartByCustomerId(customerId);

        Product product = productRepository.findById(productId)

                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (product.getAvailableStock() < quantity) {

            throw new InsufficientStockException("Insufficient stock available");

        }

        CartItem cartItem = cartItemRepository

                .findByCartCartIdAndProductProductId(cart.getCartId(), productId)

                .orElse(new CartItem(cart, product, 0, product.getFinalPrice()));

        cartItem.setQuantity(cartItem.getQuantity() + quantity);

        cartItemRepository.save(cartItem);

        return cart;

    }

    @Override

    public Cart updateCartItem(Long customerId, Long productId, int quantity) {

        Cart cart = getCartByCustomerId(customerId);

        CartItem cartItem = cartItemRepository

                .findByCartCartIdAndProductProductId(cart.getCartId(), productId)

                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        if (quantity <= 0) {

            cartItemRepository.delete(cartItem);

        } else {

            cartItem.setQuantity(quantity);

            cartItemRepository.save(cartItem);

        }

        return cart;

    }

    @Override

    public Cart removeFromCart(Long customerId, Long productId) {

        Cart cart = getCartByCustomerId(customerId);

        CartItem cartItem = cartItemRepository

                .findByCartCartIdAndProductProductId(cart.getCartId(), productId)

                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        cartItemRepository.delete(cartItem);

        return cart;
        
        

    }
    @Override

    public List<CartItem> getCartItems(Long customerId) {

        Cart cart = getCartByCustomerId(customerId);

        return cartItemRepository.findByCartCartId(cart.getCartId());

    }

}