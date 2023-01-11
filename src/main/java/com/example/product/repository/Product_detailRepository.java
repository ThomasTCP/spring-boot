package com.example.product.repository;

import com.example.product.dto.ProductDto;
import com.example.product.entity.Product_detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface Product_detailRepository extends JpaRepository<Product_detail,Long> {
    Optional<Product_detail> findByProductId(long id);
    void deleteByProductId(long id);

    @Query("select new com.example.product.dto.ProductDto(p.name,p.price,p.quantity, pd.brand) from Product p inner join Product_detail pd on p.id = pd.product.id w")
    List<ProductDto> getDetailProduct(Long productId);
}