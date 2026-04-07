package com.shruti.ecom_back.repository;

import com.shruti.ecom_back.model.Cart;
import com.shruti.ecom_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

}
