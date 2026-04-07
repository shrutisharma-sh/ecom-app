package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.dto.ProductRequest;
import com.shruti.ecom_back.dto.ProductResponse;
import com.shruti.ecom_back.model.Product;
import com.shruti.ecom_back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products")

public class AdminProductController {

    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }
}
