package com.grocery.response;

import java.time.LocalDateTime;

public class ReturnResponse {

    private Long returnRequestId;
    private Long orderId;
    private String reason;
    private String status;
    private LocalDateTime requestDate;

    private Long pickupExecutiveId;
    private String pickupExecutiveName;
    private String pickupExecutivePhone;

    public ReturnResponse(Long returnRequestId,
                          Long orderId,
                          String reason,
                          String status,
                          LocalDateTime requestDate,
                          Long pickupExecutiveId,
                          String pickupExecutiveName,
                          String pickupExecutivePhone) {

        this.returnRequestId = returnRequestId;
        this.orderId = orderId;
        this.reason = reason;
        this.status = status;
        this.requestDate = requestDate;
        this.pickupExecutiveId = pickupExecutiveId;
        this.pickupExecutiveName = pickupExecutiveName;
        this.pickupExecutivePhone = pickupExecutivePhone;
    }

	public Long getReturnRequestId() {
		return returnRequestId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public String getReason() {
		return reason;
	}

	public String getStatus() {
		return status;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public Long getPickupExecutiveId() {
		return pickupExecutiveId;
	}

	public String getPickupExecutiveName() {
		return pickupExecutiveName;
	}

	public String getPickupExecutivePhone() {
		return pickupExecutivePhone;
	}

    
}