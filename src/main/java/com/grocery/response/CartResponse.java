package com.grocery.response;

import java.util.List;

public class CartResponse {
	private Long cartId;

    private Long customerId;

    private List<CartItemResponse> items;

    private double totalAmount;

    public CartResponse(Long cartId, Long customerId,

                        List<CartItemResponse> items,

                        double totalAmount) {

        this.cartId = cartId;

        this.customerId = customerId;

        this.items = items;

        this.totalAmount = totalAmount;

    }

    public Long getCartId() {

        return cartId;

    }

    public Long getCustomerId() {

        return customerId;

    }

    public List<CartItemResponse> getItems() {

        return items;

    }

    public double getTotalAmount() {

        return totalAmount;

    }

}
