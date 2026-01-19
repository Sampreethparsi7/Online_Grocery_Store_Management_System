package com.grocery.entity;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;

import jakarta.persistence.OneToOne;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.Table;

@Entity

@Table(name = "carts")

public class Cart {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cartId;

    @OneToOne

    @JoinColumn(name = "customer_id", nullable = false)

    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)

    private List<CartItem> cartItems = new ArrayList<>();

    public Cart() {

    }

    public Cart(Customer customer) {

        this.customer = customer;

    }

    public Long getCartId() {

        return cartId;

    }

    public Customer getCustomer() {

        return customer;

    }

    public void setCustomer(Customer customer) {

        this.customer = customer;

    }

    public List<CartItem> getCartItems() {

        return cartItems;

    }

    public void setCartItems(List<CartItem> cartItems) {

        this.cartItems = cartItems;

    }

}