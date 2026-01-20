package com.grocery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "cart_items")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	
	@ManyToOne
	@JoinColumn(name = "cart_id",nullable = false)
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "product_id",nullable = false)
	private Product product;
	@Min(value = 1, message = "Quantity must be at least 1")
	@Column(nullable = false)
	private int quantity;
	
	@Column(nullable = false)
	private double price;

	
	
	public CartItem() {
		
	}

	public CartItem(Cart cart, Product product, int quantity, double price) {

		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	public Long getCartItemId() {
		return cartItemId;
	}

	

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}


}
