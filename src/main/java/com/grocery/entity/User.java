package com.grocery.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false,unique=true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String role;
	
	@Column(nullable = false)
	private String status;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	@Column(nullable = false)
	private boolean enabled=true;
	@Column(nullable = false)
	private boolean accountNonLocked=true;

	public User() {
		this.createdAt = LocalDateTime.now();
	}

	public User( String name, String email, String password, String phone, String role, String status,
			LocalDateTime createdAt) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Long getUserId() {
		return userId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}
	

	

}
