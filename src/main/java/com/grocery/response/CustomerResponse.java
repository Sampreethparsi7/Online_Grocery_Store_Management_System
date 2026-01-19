package com.grocery.response;

public class CustomerResponse {
	
	private Long customerId;
	private Long userId;
	private String name;
	private String email;
	private String phone;
	public CustomerResponse(Long customerId, Long userId, String name, String email, String phone) {
		super();
		this.customerId = customerId;
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public Long getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	
	

}
