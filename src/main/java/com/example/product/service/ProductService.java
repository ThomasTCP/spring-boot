package com.example.product.service;
import com.codepoetics.protonpack.StreamUtils;
import com.example.product.dto.ProductDto;
import com.example.product.entity.Product;
import com.example.product.entity.Product_detail;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.Product_detailRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class ProductService {
    ProductRepository productRepository;
    Product_detailRepository productDetailRepository;
    CategoryService categoryService;
    Ultilities ultilities;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDto input){
        Product product = loadFromProductDto(input,new Product());
        Product_detail productDetail = loadFromProductDto2(input, new Product_detail());
        productRepository.save(product);
        productDetailRepository.save(productDetail);
    }

    public Optional<ProductDto> getProductById(long id){
        Product product = productRepository.findById(id).get();
        Product_detail productDetail = productDetailRepository.findByProductId(id).get();
        return Optional.of(loadFromEntity(product,productDetail,new ProductDto()));
    }

    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepository.findAll();
        List<Product_detail> productDetails = productDetailRepository.findAll();
        List<ProductDto> tem = products.stream().map(p -> loadFromEntityProduct(p,new ProductDto())).collect(Collectors.toList());

    }

    public void updateProduct(long id, ProductDto input){
        Product product = loadFromProductDto(input, productRepository.findById(id).get());
        Product_detail productDetail = loadFromProductDto2(input, productDetailRepository.findByProductId(id).get());
        productRepository.save(product);
        productDetailRepository.save(productDetail);
    }

    public void deleteProductById(long id){
        productRepository.deleteById(id);
        productDetailRepository.deleteByProductId(id);
    }

    public void deleteAllProducts(){
        productRepository.deleteAll();
        productDetailRepository.deleteAll();
    }

    public ProductDto loadFromEntity(Product product, Product_detail productDetail, ProductDto productDto){
        ProductDto tem = loadFromEntityProduct(product,productDto);
        ProductDto tem2 = loadFromEntityProduct_detail(productDetail,tem);
        return tem2;
    }
    public ProductDto loadFromEntityProduct(Product product, ProductDto productDto){
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory().getId());
        productDto.setPrice(ultilities.convertPriceFormat(product.getPrice()));
        productDto.setQuantity(product.getQuantity());
        productDto.setTime_create(ultilities.convertDateFormat(product.getTime_create()));
        return productDto;
    }

    public ProductDto loadFromEntityProduct_detail(Product_detail productDetail, ProductDto productDto){
        productDto.setBrand(productDetail.getBrand());
        productDto.setDescription(productDetail.getDescription());
        return productDto;
    }

    public Product loadFromProductDto(ProductDto input, Product product){
        product.setName(input.getName());
        product.setPrice(ultilities.convertPriceFormat(input.getPrice()));
        product.setQuantity(input.getQuantity());
        try {
            product.setTime_create(ultilities.convertDateFormat2(input.getTime_create()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        product.setCategory(categoryService.getCategoryById2(input.getCategory()).get());
        return product;
    }

    public Product_detail loadFromProductDto2(ProductDto input, Product_detail productDetail){
        productDetail.setBrand(input.getBrand());
        productDetail.setDescription(input.getDescription());
        return productDetail;
    }
}