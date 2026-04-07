package com.shruti.ecom_back.controller;

import com.shruti.ecom_back.exception.BadRequestException;
import com.shruti.ecom_back.model.Category;
import com.shruti.ecom_back.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category saved = categoryService.saveCategory(category);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {

        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new BadRequestException("Category not found"));

        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Category deleted successfully");
    }
}