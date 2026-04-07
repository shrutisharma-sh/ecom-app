package com.shruti.ecom_back.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemResponse {

    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
}