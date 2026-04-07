package com.shruti.ecom_back.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartItemResponse {

    private Long cartItemId;
    private Long productId;
    private String productTitle;
    private String imageUrl;
    private BigDecimal price;
    private Integer quantity;
}
