package com.shruti.ecom_back.repository;

import com.shruti.ecom_back.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
