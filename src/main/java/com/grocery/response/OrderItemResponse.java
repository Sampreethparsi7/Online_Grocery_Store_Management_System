package com.grocery.response;

public class OrderItemResponse {
	
	private Long productId;
	private String productName;
	private int quantity;

	public OrderItemResponse(Long productId, String productName, int quantity) {
		
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;

	}
	public Long getProductId() {
		return productId;
	}
	public String getProductName() {
		return productName;
	}
	public int getQuantity() {
		return quantity;
	}

	
	
	

}
