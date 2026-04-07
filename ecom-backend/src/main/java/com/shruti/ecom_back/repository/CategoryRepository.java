package com.shruti.ecom_back.repository;

import com.shruti.ecom_back.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category ,Long> {
    Optional<Category> findByName(String name);
}
