package com.shruti.ecom_back.service;

import com.shruti.ecom_back.dto.ProductRequest;
import com.shruti.ecom_back.dto.ProductResponse;
import com.shruti.ecom_back.model.Category;
import com.shruti.ecom_back.model.Product;
import com.shruti.ecom_back.repository.CategoryRepository;
import com.shruti.ecom_back.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .title(product.getTitle())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .inStock(product.getInStock())
                        .imageUrl(product.getImageUrl())
                        .created(product.getCreated())
                        .categoryId(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .build())
                .toList();
    }
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .inStock(product.getInStock())
                .imageUrl(product.getImageUrl())
                .created(product.getCreated())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .build();
    }
    public ProductResponse createProduct(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setTitle(request.getTitle());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setInStock(request.getInStock());
        product.setImageUrl(request.getImageUrl());
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return ProductResponse.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .description(saved.getDescription())
                .price(saved.getPrice())
                .inStock(saved.getInStock())
                .imageUrl(saved.getImageUrl())
                .created(saved.getCreated())
                .categoryId(category.getId())
                .categoryName(category.getName())
                .build();
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

