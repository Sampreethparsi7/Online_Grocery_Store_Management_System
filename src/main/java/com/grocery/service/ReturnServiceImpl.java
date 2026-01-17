package com.grocery.service;

import java.util.List;
import java.util.Random;

import com.grocery.entity.DeliveryExecutive;
import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;
import com.grocery.entity.Product;
import com.grocery.entity.ReturnRequest;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.DeliveryExecutiveRepository;
import com.grocery.repository.OrderItemRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.ProductRepository;
import com.grocery.repository.ReturnRequestRepository;

public class ReturnServiceImpl implements ReturnService {
	private final ReturnRequestRepository returnRequestRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final DeliveryExecutiveRepository deliveryExecutiveRepository;

    public ReturnServiceImpl(ReturnRequestRepository returnRequestRepository,
                             OrderRepository orderRepository,
                             OrderItemRepository orderItemRepository,
                             ProductRepository productRepository,
                             DeliveryExecutiveRepository deliveryExecutiveRepository) {
        this.returnRequestRepository = returnRequestRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
    }

    @Override
    public ReturnRequest requestReturn(Long orderId, String reason) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        ReturnRequest returnRequest =
                new ReturnRequest(order, reason, "REQUESTED");

        assignDeliveryExecutive(order);

        return returnRequestRepository.save(returnRequest);
    }

    @Override
    public ReturnRequest updateReturnStatus(Long returnRequestId, String status) {
        ReturnRequest returnRequest = returnRequestRepository.findById(returnRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("Return request not found"));

        returnRequest.setStatus(status);

        if ("COMPLETED".equals(status)) {
            Order order = returnRequest.getOrder();
            List<OrderItem> orderItems =
                    orderItemRepository.findByOrderOrderId(order.getOrderId());

            for (OrderItem item : orderItems) {
                Product product = item.getProduct();
                product.setAvailableStock(
                        product.getAvailableStock() + item.getQuantity()
                );
                productRepository.save(product);
            }

            DeliveryExecutive executive = order.getDeliveryExecutive();
            if (executive != null) {
                executive.setAvailabilityStatus("AVAILABLE");
                deliveryExecutiveRepository.save(executive);
            }
        }

        return returnRequestRepository.save(returnRequest);
    }

    private void assignDeliveryExecutive(Order order) {
        List<DeliveryExecutive> availableExecutives =
                deliveryExecutiveRepository.findByAvailabilityStatus("AVAILABLE");

        if (!availableExecutives.isEmpty()) {
            DeliveryExecutive executive =
                    availableExecutives.get(new Random().nextInt(availableExecutives.size()));
            executive.setAvailabilityStatus("BUSY");
            order.setDeliveryExecutive(executive);
            deliveryExecutiveRepository.save(executive);
            orderRepository.save(order);
        }
    }

}
