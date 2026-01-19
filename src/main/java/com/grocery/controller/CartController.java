package com.grocery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;
import com.grocery.mapper.CartMapper;
import com.grocery.response.CartResponse;
import com.grocery.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartMapper cartMapper;

    public CartController(CartService cartService, CartMapper cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@RequestParam Long customerId,
                                                  @RequestParam Long productId,
                                                  @RequestParam int quantity) {

        cartService.addToCart(customerId, productId, quantity);

        Cart cart = cartService.getCartByCustomerId(customerId);
        List<CartItem> cartItems = cartService.getCartItems(customerId);

        return ResponseEntity.ok(
                cartMapper.toCartResponse(cart, cartItems)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<CartResponse> updateCartItem(@RequestParam Long customerId,
                                                       @RequestParam Long productId,
                                                       @RequestParam int quantity) {

        cartService.updateCartItem(customerId, productId, quantity);

        Cart cart = cartService.getCartByCustomerId(customerId);
        List<CartItem> cartItems = cartService.getCartItems(customerId);

        return ResponseEntity.ok(
                cartMapper.toCartResponse(cart, cartItems)
        );
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartResponse> removeFromCart(@RequestParam Long customerId,
                                                       @RequestParam Long productId) {

        cartService.removeFromCart(customerId, productId);

        Cart cart = cartService.getCartByCustomerId(customerId);
        List<CartItem> cartItems = cartService.getCartItems(customerId);

        return ResponseEntity.ok(
                cartMapper.toCartResponse(cart, cartItems)
        );
    }

    @GetMapping("/items/{customerId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long customerId) {

        Cart cart = cartService.getCartByCustomerId(customerId);
        List<CartItem> cartItems = cartService.getCartItems(customerId);

        return ResponseEntity.ok(
                cartMapper.toCartResponse(cart, cartItems)
        );
    }
}