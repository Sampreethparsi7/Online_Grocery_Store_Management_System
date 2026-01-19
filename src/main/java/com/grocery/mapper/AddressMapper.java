package com.grocery.mapper;

import org.springframework.stereotype.Component;

import com.grocery.entity.Address;

import com.grocery.response.AddressResponse;

@Component

public class AddressMapper {

    public AddressResponse toResponse(Address address) {

        return new AddressResponse(

                address.getAddressId(),

                address.getHoseNo(),

                address.getStreet(),

                address.getCity(),

                address.getState(),

                address.getPincode()

        );

    }

}