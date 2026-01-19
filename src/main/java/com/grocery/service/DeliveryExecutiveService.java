package com.grocery.service;

import com.grocery.entity.DeliveryExecutive;

public interface DeliveryExecutiveService {
	DeliveryExecutive assignAvailableExecutive();
	
	void markExecutiveAvailable(Long deliveryExecutiveId);

}
