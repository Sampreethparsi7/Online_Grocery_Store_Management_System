package com.grocery.response;

import java.time.LocalDateTime;

public class WishlistResponse {

    private Long wishlistId;

    private Long productId;

    private String productName;

    private double sellingPrice;

    private LocalDateTime addedDate;

    public WishlistResponse(Long wishlistId,

                            Long productId,

                            String productName,

                            double sellingPrice,

                            LocalDateTime addedDate) {

        this.wishlistId = wishlistId;

        this.productId = productId;

        this.productName = productName;

        this.sellingPrice = sellingPrice;

        this.addedDate = addedDate;

    }

    public Long getWishlistId() {

        return wishlistId;

    }

    public Long getProductId() {

        return productId;

    }

    public String getProductName() {

        return productName;

    }

    public double getSellingPrice() {

        return sellingPrice;

    }

    public LocalDateTime getAddedDate() {

        return addedDate;

    }

}