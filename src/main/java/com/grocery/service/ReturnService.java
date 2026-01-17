package com.grocery.service;

import com.grocery.entity.ReturnRequest;

public interface ReturnService {
	
	ReturnRequest requestReturn(Long orderId, String reason);
	ReturnRequest updateReturnStatus(Long returnRequestId, String status);
	

}
