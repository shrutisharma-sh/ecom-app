package com.shruti.ecom_back.repository;

import com.shruti.ecom_back.model.Order;
import com.shruti.ecom_back.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);
}
