package com.example.product.api;

import com.example.product.model.Product;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/product")
@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestBody Product input){
        productService.addProduct(input);
    }

    @GetMapping(path = "/{id}")
    public Optional<Product> readProductById(@PathVariable long id){
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> readAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping(path = "/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product){
        productService.updateProduct(id,product);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProductById(id);
    }
}
