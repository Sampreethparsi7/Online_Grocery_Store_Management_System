package com.grocery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.ReturnRequest;
import com.grocery.mapper.ReturnMapper;
import com.grocery.response.ReturnResponse;
import com.grocery.service.ReturnService;

@RestController

@RequestMapping("/api/returns")

public class ReturnController {

    private final ReturnService returnService;

    private final ReturnMapper returnMapper;

    public ReturnController(ReturnService returnService,

                            ReturnMapper returnMapper) {

        this.returnService = returnService;

        this.returnMapper = returnMapper;

    }

    @PostMapping("/request")

    public ResponseEntity<ReturnResponse> requestReturn(

            @RequestParam Long orderId,

            @RequestParam String reason) {

        ReturnRequest request = returnService.requestReturn(orderId, reason);

        return ResponseEntity.ok(returnMapper.toReturnResponse(request));

    }

    @PutMapping("/update-status")

    public ResponseEntity<ReturnResponse> updateReturnStatus(

            @RequestParam Long returnRequestId,

            @RequestParam String status) {

        ReturnRequest updated =

                returnService.updateReturnStatus(returnRequestId, status);

        return ResponseEntity.ok(returnMapper.toReturnResponse(updated));

    }


    @GetMapping

    public ResponseEntity<?> getAllReturns() {

        return ResponseEntity.ok(

                returnService.getAllReturns()

                        .stream()

                        .map(returnMapper::toReturnResponse)

                        .toList()

        );

    }

    @GetMapping("/assigned")

    public ResponseEntity<?> getAssignedReturns() {

        return ResponseEntity.ok(

                returnService.getAssignedReturns()

                        .stream()

                        .map(returnMapper::toReturnResponse)

                        .toList()

        );

    }

    @GetMapping("/unassigned")

    public ResponseEntity<?> getUnassignedReturns() {

        return ResponseEntity.ok(

                returnService.getUnassignedReturns()

                        .stream()

                        .map(returnMapper::toReturnResponse)

                        .toList()

        );

    }

}