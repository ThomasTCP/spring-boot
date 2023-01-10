package com.example.product.repository;

import com.example.product.model.Product_detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDetailRepository extends JpaRepository<Product_detail,Long> {
    Optional<Product_detail> findByProductId(long id);
    void deleteByProductId(long id);
}