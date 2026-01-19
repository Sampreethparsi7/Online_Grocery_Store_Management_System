package com.grocery.response;

public class DeliveryExecutiveResponse {
	private Long deliveryExecutiveId;

    private String name;

    private String phone;

    private String availabilityStatus;

    public DeliveryExecutiveResponse(Long deliveryExecutiveId,

                                     String name,

                                     String phone,

                                     String availabilityStatus) {

        this.deliveryExecutiveId = deliveryExecutiveId;

        this.name = name;

        this.phone = phone;

        this.availabilityStatus = availabilityStatus;

    }

    public Long getDeliveryExecutiveId() {

        return deliveryExecutiveId;

    }

    public String getName() {

        return name;

    }

    public String getPhone() {

        return phone;

    }

    public String getAvailabilityStatus() {

        return availabilityStatus;

    }

}
