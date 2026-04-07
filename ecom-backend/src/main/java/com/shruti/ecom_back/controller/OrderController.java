package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.dto.OrderResponse;
import com.shruti.ecom_back.exception.BadRequestException;
import com.shruti.ecom_back.model.Order;
import com.shruti.ecom_back.model.User;
import com.shruti.ecom_back.repository.UserRepository;
import com.shruti.ecom_back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderResponse order = orderService.placeOrder(user);

        return ResponseEntity.ok(order);
    }
    @GetMapping
    public ResponseEntity<?> getUserOrders(Authentication authentication) {

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return ResponseEntity.ok(orderService.getUserOrders(user));
    }

}
