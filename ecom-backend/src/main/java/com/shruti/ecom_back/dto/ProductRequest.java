package com.shruti.ecom_back.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String title;
    private String description;
    private BigDecimal price;
    private Boolean inStock;
    private String imageUrl;

    private Long categoryId;
}
