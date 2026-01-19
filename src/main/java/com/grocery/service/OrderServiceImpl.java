package com.grocery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery.entity.Address;
import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;
import com.grocery.entity.Customer;
import com.grocery.entity.DeliveryExecutive;
import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;
import com.grocery.entity.Product;
import com.grocery.exception.InsufficientStockException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.AddressRepository;
import com.grocery.repository.CartItemRepository;
import com.grocery.repository.CartRepository;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.OrderItemRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DeliveryExecutiveService deliveryExecutiveService;
    private final AddressRepository addressRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            OrderItemRepository orderItemRepository,
                            CartRepository cartRepository,
                            CartItemRepository cartItemRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            DeliveryExecutiveService deliveryExecutiveService,
                            AddressRepository addressRepository) {

        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.deliveryExecutiveService = deliveryExecutiveService;
        this.addressRepository = addressRepository;
    }

    @Override
    public Order placeOrder(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Cart cart = cartRepository.findByCustomerCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItem> cartItems =
                cartItemRepository.findByCartCartId(cart.getCartId());

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        
        List<Address> addresses =
                addressRepository.findByCustomerCustomerId(customerId);

        if (addresses.isEmpty()) {
            throw new RuntimeException("Customer address not found");
        }

        Address address = addresses.get(0);

        double totalMrp = 0;
        double totalDiscount = 0;

        for (CartItem item : cartItems) {
            Product product = item.getProduct();

            if (product.getAvailableStock() < item.getQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock for product: " + product.getProductName());
            }

            totalMrp += product.getMrp() * item.getQuantity();
            totalDiscount +=
                    (product.getMrp() - product.getFinalPrice()) * item.getQuantity();
        }

        
        Order order = new Order(
                customer,
                "ORDER_PLACED",
                totalMrp,
                totalDiscount,
                totalMrp - totalDiscount,
                customer.getUser().getName(),
                customer.getUser().getPhone(),
                address.getHoseNo() + ", " + address.getStreet(),
                address.getCity(),
                address.getPincode()
        );

     
        DeliveryExecutive executive =
                deliveryExecutiveService.assignAvailableExecutive();

        order.setDeliveryExecutive(executive);
        order.setStatus("OUT_FOR_DELIVERY");

        Order savedOrder = orderRepository.save(order);

     
        for (CartItem item : cartItems) {

            Product product = item.getProduct();
            product.setAvailableStock(
                    product.getAvailableStock() - item.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(
                    savedOrder,
                    product,
                    item.getQuantity(),
                    item.getPrice()
            );

            orderItemRepository.save(orderItem);
        }

  
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByCustomer(Long customerId) {
        return orderRepository.findByCustomerCustomerId(customerId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {

        Order order = getOrderById(orderId);
        order.setStatus(status);

        if ("DELIVERED".equalsIgnoreCase(status)
                && order.getDeliveryExecutive() != null) {

            deliveryExecutiveService.markExecutiveAvailable(
                    order.getDeliveryExecutive().getDeliveryexecutiveId());
        }

        return orderRepository.save(order);
    }
}