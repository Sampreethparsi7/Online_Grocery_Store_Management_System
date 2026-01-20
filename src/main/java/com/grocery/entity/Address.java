package com.grocery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id",nullable = false)
	private Customer customer;
	
	@NotBlank(message = "House number cannot be empty")
	@Column(nullable = false)
	private String hoseNo;
	
	@NotBlank(message = "Street cannot be empty")
	@Column(nullable = false)
	private String street;
	
	 @NotBlank(message = "City cannot be empty")
	@Column(nullable = false)
	private String city;
	
	 @NotBlank(message = "State cannot be empty")
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String pincode;

	public Address() {
		
	}

	public Address(Customer customer, String hoseNo, String street, String city, String state,
			String pincode) {
		
		this.customer = customer;
		this.hoseNo = hoseNo;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public Long getAddressId() {
		return addressId;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getHoseNo() {
		return hoseNo;
	}

	public void setHoseNo(String hoseNo) {
		this.hoseNo = hoseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	
	

}
