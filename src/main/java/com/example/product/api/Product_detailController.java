package com.example.product.api;

import com.example.product.model.Product_detail;
import com.example.product.service.Product_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/product_")
@RestController
public class Product_detailController {
    private Product_detailService productDetailService;

    @Autowired
    public Product_detailController(Product_detailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping
    public void createProduct_(Product_detail input){
        productDetailService.addProduct_(input);
    }

    @GetMapping(path = "/{id}")
    public Optional<Product_detail> readProductById(@PathVariable long id){
        return productDetailService.getProduct_ByProductId(id);
    }

    @PutMapping(path = "/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product_detail product){
        productDetailService.updateProduct_ByProductId(id,product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable long id){
        productDetailService.deleteProduct_ByProductId(id);
    }
}
