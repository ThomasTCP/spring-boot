package com.example.product.service;

import com.example.product.dto.ProductDto;
import com.example.product.dto.ProductResponse;
import com.example.product.entity.Product;
import com.example.product.entity.ProductDetail;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.ProductDetailRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private CategoryService categoryService;

    //create
    @SneakyThrows
    public void addProduct(ProductResponse productResponse) {
        Product product = new Product();
        product.setName(productResponse.getName());
        product.setPrice(Ultilities.convertPriceFormat(productResponse.getPrice()));
        product.setQuantity(productResponse.getQuantity());
        product.setTime_create(Ultilities.convertDateFormat2(productResponse.getDate()));
        product.setCategory(categoryService.getCategoryById(productResponse.getCategory_id()).get());

        ProductDetail productDetail = new ProductDetail();
        productDetail.setBrand(productResponse.getBrand());
        productDetail.setDescription(productResponse.getDescription());
        productDetail.setProduct(product);

        productRepository.save(product);
        productDetailRepository.save(productDetail);
    }

    //read 1
    public ProductDto getProductDtoById(Long id) {
        Product product = productRepository.findById(id).get();
        ProductDetail productDetail = productDetailRepository.findByProductId(id).get();
        return new ProductDto(
                product.getName(),
                product.getCategory().getId(),
                Ultilities.convertPriceFormat(product.getPrice()),
                product.getQuantity(),
                Ultilities.convertDateFormat(product.getTime_create()),
                productDetail.getBrand(),
                productDetail.getDescription()
        );
    }

    //read 2
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDetail> productDetails = productDetailRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            for (ProductDetail productDetail : productDetails) {
                if (product.getId().equals(productDetail.getProduct().getId())) {
                    productDtos.add(new ProductDto(
                            product.getName(),
                            product.getCategory().getId(),
                            Ultilities.convertPriceFormat(product.getPrice()),
                            product.getQuantity(),
                            Ultilities.convertDateFormat(product.getTime_create()),
                            productDetail.getBrand(),
                            productDetail.getDescription()
                    ));
                }
            }
        }
        return productDtos;
    }

    //update
    @SneakyThrows
    public void updateProduct(Long id, ProductResponse productResponse) {
        Product product = productRepository.findById(id).get();
        ProductDetail productDetail = productDetailRepository.findByProductId(id).get();
        product.setName(productResponse.getName());
        product.setPrice(Ultilities.convertPriceFormat(productResponse.getPrice()));
        product.setQuantity(productResponse.getQuantity());
        product.setTime_create(Ultilities.convertDateFormat2(productResponse.getDate()));
        product.setCategory(categoryService.getCategoryById(productResponse.getCategory_id()).get());
        productDetail.setBrand(productResponse.getBrand());
        productDetail.setDescription(productResponse.getDescription());
        productDetail.setProduct(product);
        productRepository.save(product);
        productDetailRepository.save(productDetail);
    }

    //delete 1
    public void deleteProductById(Long id) {
        productDetailRepository.deleteByProductId(id);
        productRepository.deleteById(id);
    }

}