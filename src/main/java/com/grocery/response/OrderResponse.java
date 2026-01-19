package com.grocery.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {

    private Long orderId;
    private String status;
    private LocalDateTime orderPlacedDate;
    private String customerName;
    private String customerPhone;
    
    private double totalMrp;
    private double totalDiscount;
    private double finalAmount;
    private DeliveryAddressResponse deliveryAddress;
    private DeliveryExecutiveResponse deliveryExecutive;
    private List<OrderItemResponse> items;
	public OrderResponse(Long orderId, String status, LocalDateTime orderPlacedDate, String customerName,
			String customerPhone, double totalMrp, double totalDiscount, double finalAmount,
			DeliveryAddressResponse deliveryAddress, DeliveryExecutiveResponse deliveryExecutive,
			List<OrderItemResponse> items) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.orderPlacedDate = orderPlacedDate;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.totalMrp = totalMrp;
		this.totalDiscount = totalDiscount;
		this.finalAmount = finalAmount;
		this.deliveryAddress = deliveryAddress;
		this.deliveryExecutive = deliveryExecutive;
		this.items = items;
	}
	public Long getOrderId() {
		return orderId;
	}
	public String getStatus() {
		return status;
	}
	public LocalDateTime getOrderPlacedDate() {
		return orderPlacedDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public double getTotalMrp() {
		return totalMrp;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public DeliveryAddressResponse getDeliveryAddress() {
		return deliveryAddress;
	}
	public DeliveryExecutiveResponse getDeliveryExecutive() {
		return deliveryExecutive;
	}
	public List<OrderItemResponse> getItems() {
		return items;
	}
    
    

    
}