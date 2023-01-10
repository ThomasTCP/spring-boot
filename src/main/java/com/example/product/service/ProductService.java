package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product input){
        productRepository.save(input);
    }

    public Optional<Product> getProductById(long id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void updateProduct(long id, Product input){
        Product product = productRepository.findById(id).get();
        product.setCategory(input.getCategory());
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());
        productRepository.save(product);
    }

    public void deleteProductById(long id){
        productRepository.deleteById(id);
    }

    public void deleteAllProducts(){
        productRepository.deleteAll();
    }
}