package com.example.product.repository;

import com.example.product.dto.ProductDto;
import com.example.product.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long>, CrudRepository<ProductDetail,Long> {
    Optional<ProductDetail> findByProductId(long id);
    @Transactional
    void deleteByProductId(Long id);
}