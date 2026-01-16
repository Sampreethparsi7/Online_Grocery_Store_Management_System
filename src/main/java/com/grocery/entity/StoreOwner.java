package com.grocery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "store_owners")
public class StoreOwner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ownerId;
	
	@OneToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;

	public StoreOwner() {
		
	}
	
	public StoreOwner(User user) {
		this.user = user;
	}
	
	public Long getOwnerId() {
		return ownerId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
