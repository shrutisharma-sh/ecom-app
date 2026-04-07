package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.dto.ProductResponse;
import com.shruti.ecom_back.model.Product;
import com.shruti.ecom_back.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/products")
public class PublicProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
