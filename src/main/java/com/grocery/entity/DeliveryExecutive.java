package com.grocery.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery_executives")
public class DeliveryExecutive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long deliveryexecutiveId;
	
	@OneToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@Column(nullable = false)
	private String availabilityStatus;
	
	public DeliveryExecutive() {
		
	}

	public DeliveryExecutive(User user, String availabilityStatus) {
		
		this.user = user;
		this.availabilityStatus = availabilityStatus;
	}

	public Long getDeliveryexecutiveId() {
		return deliveryexecutiveId;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	
	
	
	

}
