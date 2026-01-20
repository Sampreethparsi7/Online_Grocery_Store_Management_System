package com.grocery.mapper;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.grocery.entity.Cart;

import com.grocery.entity.CartItem;

import com.grocery.response.CartItemResponse;

import com.grocery.response.CartResponse;

@Component

public class CartMapper {

    public CartResponse toCartResponse(Cart cart, List<CartItem> cartItems) {

        List<CartItemResponse> itemResponses = cartItems.stream()

                .map(item -> {

                    double lineTotal = item.getPrice() * item.getQuantity();

                    return new CartItemResponse(

                            item.getProduct().getProductId(),

                            item.getProduct().getProductName(),

                            item.getQuantity(),

                            item.getPrice(),

                            lineTotal

                    );

                })

                .collect(Collectors.toList());

        double totalAmount = itemResponses.stream()

                .mapToDouble(CartItemResponse::getLineTotal)

                .sum();

        return new CartResponse(

                cart.getCartId(),

                cart.getCustomer().getCustomerId(),

                itemResponses,

                totalAmount

        );

    }

}