package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Address;

import com.grocery.entity.Customer;

import com.grocery.exception.ResourceNotFoundException;

import com.grocery.repository.AddressRepository;

import com.grocery.repository.CustomerRepository;

@Service

public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    public AddressServiceImpl(AddressRepository addressRepository,

                              CustomerRepository customerRepository) {

        this.addressRepository = addressRepository;

        this.customerRepository = customerRepository;

    }

    @Override

    public Address addAddress(Long customerId, Address address) {

        Customer customer = customerRepository.findById(customerId)

                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        address.setCustomer(customer);

        return addressRepository.save(address);

    }

    @Override

    public List<Address> getAddressesByCustomer(Long customerId) {

        return addressRepository.findByCustomerCustomerId(customerId);

    }

    @Override

    public Address updateAddress(Long customerId, Long addressId, Address updatedAddress) {

        Address existingAddress = addressRepository.findById(addressId)

                .orElseThrow(() -> new ResourceNotFoundException("Address not found"));

        if (!existingAddress.getCustomer().getCustomerId().equals(customerId)) {

            throw new ResourceNotFoundException("Address does not belong to this customer");

        }

        existingAddress.setHoseNo(updatedAddress.getHoseNo());

        existingAddress.setStreet(updatedAddress.getStreet());

        existingAddress.setCity(updatedAddress.getCity());

        existingAddress.setState(updatedAddress.getState());

        existingAddress.setPincode(updatedAddress.getPincode());

        return addressRepository.save(existingAddress);

    }

}