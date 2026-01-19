package com.grocery.response;

public class AddressResponse {
	private Long addressId;

    private String hoseNo;

    private String street;

    private String city;

    private String state;

    private String pincode;

    public AddressResponse(Long addressId, String hoseNo, String street,

                           String city, String state, String pincode) {

        this.addressId = addressId;

        this.hoseNo = hoseNo;

        this.street = street;

        this.city = city;

        this.state = state;

        this.pincode = pincode;

    }

    public Long getAddressId() {

        return addressId;

    }

    public String getHoseNo() {

        return hoseNo;

    }

    public String getStreet() {

        return street;

    }

    public String getCity() {

        return city;

    }

    public String getState() {

        return state;

    }

    public String getPincode() {

        return pincode;

    }

}
