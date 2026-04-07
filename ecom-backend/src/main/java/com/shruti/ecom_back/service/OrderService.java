package com.shruti.ecom_back.service;

import com.shruti.ecom_back.dto.OrderItemResponse;
import com.shruti.ecom_back.dto.OrderResponse;
import com.shruti.ecom_back.exception.BadRequestException;
import com.shruti.ecom_back.model.*;
import com.shruti.ecom_back.repository.CartRepository;
import com.shruti.ecom_back.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(User user) {

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new BadRequestException("Cart not found"));

        if (cart.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());

            BigDecimal price = cartItem.getProduct().getPrice();
            item.setPrice(price);

            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            total = total.add(itemTotal);

            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        // clear cart
        cart.getItems().clear();
        cartRepository.save(cart);

        return mapToResponse(savedOrder);
    }

    public List<OrderResponse> getUserOrders(User user) {

        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public OrderResponse updateOrderStatus(Long orderId, String status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));

        Order updated = orderRepository.save(order);

        return mapToResponse(updated);
    }

    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getId());
        response.setStatus(order.getStatus().name());
        response.setTotalAmount(order.getTotalAmount());
        response.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponse> items = new ArrayList<>();

        for (OrderItem item : order.getItems()) {

            OrderItemResponse itemResponse = new OrderItemResponse();
            itemResponse.setProductId(item.getProduct().getId());
            itemResponse.setProductName(item.getProduct().getTitle());
            itemResponse.setQuantity(item.getQuantity());
            itemResponse.setPrice(item.getPrice());

            items.add(itemResponse);
        }

        response.setItems(items);

        return response;
    }
}