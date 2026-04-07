package com.shruti.ecom_back.repository;

import com.shruti.ecom_back.model.Cart;
import com.shruti.ecom_back.model.CartItem;
import com.shruti.ecom_back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
