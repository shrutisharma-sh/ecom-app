package com.shruti.ecom_back.repository;


import com.shruti.ecom_back.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
