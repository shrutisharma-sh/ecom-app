package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.exception.BadRequestException;
import com.shruti.ecom_back.model.Category;
import com.shruti.ecom_back.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));
    }

}
