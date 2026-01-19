package com.grocery.service;

import java.util.List;

import com.grocery.entity.Address;

public interface AddressService {
	Address addAddress(Long customerId, Address address);
	Address updateAddress(Long customerId,Long addressId, Address address);
    List<Address> getAddressesByCustomer(Long customerId);

}
