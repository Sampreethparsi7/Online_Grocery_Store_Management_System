package com.grocery.response;

public class DeliveryAddressResponse {
	
	private String addressLine;
	private String city;
	private String pincode;
	public DeliveryAddressResponse(String addressLine, String city, String pincode) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.pincode = pincode;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public String getCity() {
		return city;
	}
	public String getPincode() {
		return pincode;
	}
	
	

}
