package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @PutMapping("/{orderId}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable Long orderId,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }
}