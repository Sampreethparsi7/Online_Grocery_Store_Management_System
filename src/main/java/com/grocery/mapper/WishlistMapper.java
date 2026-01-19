package com.grocery.mapper;

import org.springframework.stereotype.Component;

import com.grocery.entity.Wishlist;

import com.grocery.response.WishlistResponse;

@Component

public class WishlistMapper {

    public WishlistResponse toWishlistResponse(Wishlist wishlist) {

        return new WishlistResponse(

                wishlist.getWishlistId(),

                wishlist.getProduct().getProductId(),

                wishlist.getProduct().getProductName(),

                wishlist.getProduct().getSellingPrice(),

                wishlist.getAddedDate()

        );

    }

}