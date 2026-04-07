package com.shruti.ecom_back.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CartResponse {
    private Long cartId;
    private List<CartItemResponse> items;

    private Integer totalItems;
    private BigDecimal totalPrice;
}
