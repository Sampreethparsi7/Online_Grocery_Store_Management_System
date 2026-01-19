package com.grocery.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.mapper.WishlistMapper;
import com.grocery.response.WishlistResponse;
import com.grocery.service.WishlistService;

@RestController

@RequestMapping("/api/wishlist")

public class WishlistController {

    private final WishlistService wishlistService;

    private final WishlistMapper wishlistMapper;

    public WishlistController(WishlistService wishlistService,

                              WishlistMapper wishlistMapper) {

        this.wishlistService = wishlistService;

        this.wishlistMapper = wishlistMapper;

    }

    @PostMapping("/add")

    public ResponseEntity<WishlistResponse> addToWishlist(

            @RequestParam Long customerId,

            @RequestParam Long productId) {

        return ResponseEntity.ok(

                wishlistMapper.toWishlistResponse(

                        wishlistService.addToWishlist(customerId, productId)

                )

        );

    }


    @GetMapping("/customer/{customerId}")

    public ResponseEntity<List<WishlistResponse>> getWishlistByCustomer(

            @PathVariable Long customerId) {

        List<WishlistResponse> responses =

                wishlistService.getWishlistByCustomer(customerId)

                        .stream()

                        .map(wishlistMapper::toWishlistResponse)

                        .collect(Collectors.toList());

        return ResponseEntity.ok(responses);

    }

    @DeleteMapping("/remove/{wishlistId}")

    public ResponseEntity<Void> removeFromWishlist(

            @PathVariable Long wishlistId) {

        wishlistService.removeFromWishlist(wishlistId);

        return ResponseEntity.noContent().build();

    }
    @PostMapping("/move-to-cart")

    public ResponseEntity<Void> moveToCart(

            @RequestParam Long wishlistId) {
    	
    	wishlistService.moveToCart(wishlistId);
        return ResponseEntity.ok().build();

    }

}