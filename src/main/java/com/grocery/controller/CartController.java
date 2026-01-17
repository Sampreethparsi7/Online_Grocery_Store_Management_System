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
import com.grocery.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addToCart(@RequestParam Long customerId,
                                              @RequestParam Long productId,
                                              @RequestParam int quantity) {
        return ResponseEntity.ok(
                cartService.addItemToCart(customerId, productId, quantity)
        );
    }
    
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable Long cartItemId,
                                                   @RequestParam int quantity) {
        return ResponseEntity.ok(
                cartService.updateCartItem(cartItemId, quantity)
        );
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @GetMapping("/items/{customerId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCartItems(customerId));
    }

}
