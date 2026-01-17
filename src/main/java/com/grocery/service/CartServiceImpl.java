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
    public CartItem addItemToCart(Long customerId, Long productId, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        if (product.getAvailableStock() < quantity) {
            throw new InsufficientStockException("Insufficient stock available");
        }
        CartItem cartItem = new CartItem(
                cart,
                product,
                quantity,
                product.getFinalPrice()
        );
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, int quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItemRepository.delete(cartItem);
    }

    @Override
    public List<CartItem> getCartItems(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        return cartItemRepository.findByCartCartId(cart.getCartId());
    }

}
