package com.grocery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "delivery_executive_id")
	private DeliveryExecutive deliveryExecutive;

	@Column(nullable = false)
	private LocalDateTime orderPlacedDate;

	@Column(nullable = false)

	private String status;

	@Column(nullable = false)

	private double totalMrp;

	@Column(nullable = false)

	private double totalDiscount;

	@Column(nullable = false)

	private double finalAmount;

	public Order() {

		this.orderPlacedDate = LocalDateTime.now();

	}

	public Order(Customer customer, String status, double totalMrp, double totalDiscount, double finalAmount) {
		this.customer = customer;
		this.status = status;
		this.totalMrp = totalMrp;
		this.totalDiscount = totalDiscount;
		this.finalAmount = finalAmount;
		this.orderPlacedDate = LocalDateTime.now();
	}

	public Long getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DeliveryExecutive getDeliveryExecutive() {
		return deliveryExecutive;
	}

	public void setDeliveryExecutive(DeliveryExecutive deliveryExecutive) {
		this.deliveryExecutive = deliveryExecutive;
	}

	public LocalDateTime getOrderPlacedDate() {
		return orderPlacedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}

}
