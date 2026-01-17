package com.grocery.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.grocery.entity.Cart;
import com.grocery.entity.CartItem;
import com.grocery.entity.Customer;
import com.grocery.entity.DeliveryExecutive;
import com.grocery.entity.Order;
import com.grocery.entity.OrderItem;
import com.grocery.entity.Product;
import com.grocery.exception.InsufficientStockException;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.CartItemRepository;
import com.grocery.repository.CartRepository;
import com.grocery.repository.CustomerRepository;
import com.grocery.repository.DeliveryExecutiveRepository;
import com.grocery.repository.OrderItemRepository;
import com.grocery.repository.OrderRepository;
import com.grocery.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService{
	 private final OrderRepository orderRepository;
	    private final OrderItemRepository orderItemRepository;
	    private final CartRepository cartRepository;
	    private final CartItemRepository cartItemRepository;
	    private final CustomerRepository customerRepository;
	    private final DeliveryExecutiveRepository deliveryExecutiveRepository;
	    private final ProductRepository productRepository;
	    public OrderServiceImpl(OrderRepository orderRepository,
	                            OrderItemRepository orderItemRepository,
	                            CartRepository cartRepository,
	                            CartItemRepository cartItemRepository,
	                            CustomerRepository customerRepository,
	                            DeliveryExecutiveRepository deliveryExecutiveRepository,
	                            ProductRepository productRepository) {
	        this.orderRepository = orderRepository;
	        this.orderItemRepository = orderItemRepository;
	        this.cartRepository = cartRepository;
	        this.cartItemRepository = cartItemRepository;
	        this.customerRepository = customerRepository;
	        this.deliveryExecutiveRepository = deliveryExecutiveRepository;
	        this.productRepository = productRepository;
	    }

	    @Override
	    public Order placeOrder(Long customerId) {
	        Customer customer = customerRepository.findById(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
	        Cart cart = cartRepository.findByCustomerCustomerId(customerId)
	                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
	        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cart.getCartId());
	        if (cartItems.isEmpty()) {
	            throw new RuntimeException("Cart is empty");
	        }

	        double totalMrp = 0;
	        double totalDiscount = 0;
	        for (CartItem item : cartItems) {
	            Product product = item.getProduct();
	            if (product.getAvailableStock() < item.getQuantity()) {
	                throw new InsufficientStockException("Insufficient stock for product");
	            }
	            totalMrp += product.getMrp() * item.getQuantity();
	            totalDiscount += (product.getMrp() - product.getFinalPrice()) * item.getQuantity();
	        }

	        Order order = new Order(customer, "ORDER_PLACED", totalMrp, totalDiscount,
	                totalMrp - totalDiscount);
	        assignDeliveryExecutive(order);
	        Order savedOrder = orderRepository.save(order);
	        for (CartItem item : cartItems) {
	            Product product = item.getProduct();
	            product.setAvailableStock(product.getAvailableStock() - item.getQuantity());
	            productRepository.save(product);
	            OrderItem orderItem = new OrderItem(savedOrder, product,
	                    item.getQuantity(), item.getPrice());
	            orderItemRepository.save(orderItem);
	        }
	        cartItemRepository.deleteAll(cartItems);
	        return savedOrder;
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
	        }
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
	        return orderRepository.save(order);
	    }

}
