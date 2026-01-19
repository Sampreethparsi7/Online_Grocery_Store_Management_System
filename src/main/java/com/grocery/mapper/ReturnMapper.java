package com.grocery.mapper;

import org.springframework.stereotype.Component;

import com.grocery.entity.ReturnRequest;

import com.grocery.response.ReturnResponse;

@Component

public class ReturnMapper {

    public ReturnResponse toReturnResponse(ReturnRequest request) {

        Long execId = null;

        String execName = null;

        String execPhone = null;

        if (request.getPickupExecutive() != null) {

            execId = request.getPickupExecutive().getDeliveryexecutiveId();

            execName = request.getPickupExecutive().getUser().getName();

            execPhone = request.getPickupExecutive().getUser().getPhone();

        }

        return new ReturnResponse(

                request.getReturnRequestsId(),

                request.getOrder().getOrderId(),

                request.getReason(),

                request.getStatus(),

                request.getRequestDate(),

                execId,

                execName,

                execPhone

        );

    }

}