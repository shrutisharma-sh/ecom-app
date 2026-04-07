package com.shruti.ecom_back.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Boolean inStock;
    private String imageUrl;
    private Date created;

    private Long categoryId;
    private String categoryName;
}
