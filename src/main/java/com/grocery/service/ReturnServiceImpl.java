package com.grocery.service;

import java.util.List;

import java.util.Random;

import org.springframework.stereotype.Service;

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

@Service

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

        boolean alreadyRequested =

                returnRequestRepository.existsByOrderOrderIdAndStatusIn(

                        orderId,

                        List.of("REQUESTED", "PICKED_UP", "IN_PROGRESS")

                );

        if (alreadyRequested) {

            throw new IllegalStateException("Return already requested for this order");

        }

        Order order = orderRepository.findById(orderId)

                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        ReturnRequest request = new ReturnRequest(order, reason, "REQUESTED");

        assignPickupExecutive(request);

        return returnRequestRepository.save(request);

    }

    @Override

    public ReturnRequest updateReturnStatus(Long returnRequestId, String status) {

        ReturnRequest request = returnRequestRepository.findById(returnRequestId)

                .orElseThrow(() -> new ResourceNotFoundException("Return request not found"));

        request.setStatus(status);

        if ("COMPLETED".equalsIgnoreCase(status)) {

            Order order = request.getOrder();

            List<OrderItem> items =

                    orderItemRepository.findByOrderOrderId(order.getOrderId());

          

            for (OrderItem item : items) {

                Product product = item.getProduct();

                product.setAvailableStock(product.getAvailableStock() + item.getQuantity());

                productRepository.save(product);

            }

        

            if (request.getPickupExecutive() != null) {

                DeliveryExecutive exec = request.getPickupExecutive();

                exec.setAvailabilityStatus("AVAILABLE");

                deliveryExecutiveRepository.save(exec);

            }

        }

        return returnRequestRepository.save(request);

    }

  

    private void assignPickupExecutive(ReturnRequest request) {

        Order order = request.getOrder();

        DeliveryExecutive orderExec = order.getDeliveryExecutive();

        List<DeliveryExecutive> available =

                deliveryExecutiveRepository.findByAvailabilityStatus("AVAILABLE");

        available.remove(orderExec); // 🔥 important

        if (!available.isEmpty()) {

            DeliveryExecutive exec =

                    available.get(new Random().nextInt(available.size()));

            exec.setAvailabilityStatus("BUSY");

            request.setPickupExecutive(exec);

            deliveryExecutiveRepository.save(exec);

        }

    }



    public List<ReturnRequest> getAllReturns() {

        return returnRequestRepository.findAll();

    }

    public List<ReturnRequest> getAssignedReturns() {

        return returnRequestRepository.findByPickupExecutiveIsNotNull();

    }

    public List<ReturnRequest> getUnassignedReturns() {

        return returnRequestRepository.findByPickupExecutiveIsNull();

    }

}