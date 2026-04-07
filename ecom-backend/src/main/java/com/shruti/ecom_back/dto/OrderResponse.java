package com.shruti.ecom_back.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long orderId;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    private List<OrderItemResponse> items;
}