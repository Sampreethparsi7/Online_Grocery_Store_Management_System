package com.grocery.service;

import java.util.List;

import com.grocery.entity.ReturnRequest;

public interface ReturnService {

    ReturnRequest requestReturn(Long orderId, String reason);

    ReturnRequest updateReturnStatus(Long returnRequestId, String status);

    List<ReturnRequest> getAllReturns();

    List<ReturnRequest> getAssignedReturns();

    List<ReturnRequest> getUnassignedReturns();
}