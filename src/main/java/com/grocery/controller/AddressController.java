package com.grocery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grocery.entity.Address;
import com.grocery.mapper.AddressMapper;
import com.grocery.response.AddressResponse;
import com.grocery.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(AddressService addressService,
                             AddressMapper addressMapper) {
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addAddress(
            @RequestParam Long customerId,
            @RequestBody Address address) {

        Address savedAddress = addressService.addAddress(customerId, address);
        return ResponseEntity.ok(addressMapper.toResponse(savedAddress));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AddressResponse>> getAddressesByCustomer(
            @PathVariable Long customerId) {

        List<AddressResponse> responses = addressService
                .getAddressesByCustomer(customerId)
                .stream()
                .map(addressMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<AddressResponse> updateAddress(
            @RequestParam Long customerId,
            @PathVariable Long addressId,
            @RequestBody Address address) {

        Address updated = addressService.updateAddress(customerId, addressId, address);
        return ResponseEntity.ok(addressMapper.toResponse(updated));
    }
}