package com.example.product.service;

import com.example.product.model.Product_detail;
import com.example.product.repository.ProductDetailRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Product_detailService {
    ProductDetailRepository productDetailRepository;

    public void addProduct_(Product_detail input){
        productDetailRepository.save(input);
    }

    public Optional<Product_detail> getProduct_ByProductId(long id){
        return productDetailRepository.findByProductId(id);
    }

    public void updateProduct_ByProductId(long id, Product_detail input){
        Product_detail product_ = productDetailRepository.findByProductId(id).get();
        product_.setBrand(input.getBrand());
        product_.setDescription(input.getDescription());
        productDetailRepository.save(product_);
    }

    public void deleteProduct_ByProductId(long id){
        productDetailRepository.deleteByProductId(id);
    }
}
