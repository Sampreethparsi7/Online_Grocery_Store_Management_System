package com.grocery.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

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


    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerPhone;


    @Column(nullable = false)
    private String deliveryAddressLine;

    @Column(nullable = false)
    private String deliveryCity;

    @Column(nullable = false)
    private String deliveryPincode;

    public Order() {
        this.orderPlacedDate = LocalDateTime.now();
    }

    public Order(Customer customer,
                 String status,
                 double totalMrp,
                 double totalDiscount,
                 double finalAmount,
                 String customerName,
                 String customerPhone,
                 String deliveryAddressLine,
                 String deliveryCity,
                 String deliveryPincode) {

        this.customer = customer;
        this.status = status;
        this.totalMrp = totalMrp;
        this.totalDiscount = totalDiscount;
        this.finalAmount = finalAmount;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.deliveryAddressLine = deliveryAddressLine;
        this.deliveryCity = deliveryCity;
        this.deliveryPincode = deliveryPincode;
        this.orderPlacedDate = LocalDateTime.now();
    }

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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

	public void setOrderPlacedDate(LocalDateTime orderPlacedDate) {
		this.orderPlacedDate = orderPlacedDate;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getDeliveryAddressLine() {
		return deliveryAddressLine;
	}

	public void setDeliveryAddressLine(String deliveryAddressLine) {
		this.deliveryAddressLine = deliveryAddressLine;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryPincode() {
		return deliveryPincode;
	}

	public void setDeliveryPincode(String deliveryPincode) {
		this.deliveryPincode = deliveryPincode;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    
}