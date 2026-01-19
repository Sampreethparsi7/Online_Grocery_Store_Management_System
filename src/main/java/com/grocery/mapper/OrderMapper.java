package com.grocery.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.grocery.entity.Order;
import com.grocery.response.DeliveryAddressResponse;
import com.grocery.response.DeliveryExecutiveResponse;
import com.grocery.response.OrderItemResponse;
import com.grocery.response.OrderResponse;

@Component

public class OrderMapper {

    public OrderResponse toOrderResponse(Order order) {

        DeliveryAddressResponse address = new DeliveryAddressResponse(

                order.getDeliveryAddressLine(),

                order.getDeliveryCity(),

                order.getDeliveryPincode()

        );

        DeliveryExecutiveResponse executive = null;

        if (order.getDeliveryExecutive() != null) {

            executive = new DeliveryExecutiveResponse(

                    order.getDeliveryExecutive().getDeliveryexecutiveId(),

                    order.getDeliveryExecutive().getUser().getName(),

                    order.getDeliveryExecutive().getUser().getPhone(),

                    order.getDeliveryExecutive().getAvailabilityStatus()

            );

        }

        List<OrderItemResponse> items =

                order.getOrderItems()

                     .stream()

                     .map(item -> new OrderItemResponse(

                             item.getProduct().getProductId(),

                             item.getProduct().getProductName(),

                             item.getQuantity()

                     ))

                     .collect(Collectors.toList());

        return new OrderResponse(

                order.getOrderId(),

                order.getStatus(),

                order.getOrderPlacedDate(),

                order.getCustomerName(),

                order.getCustomerPhone(),

                order.getTotalMrp(),

                order.getTotalDiscount(),

                order.getFinalAmount(),

                address,

                executive,

                items

        );

    }

}