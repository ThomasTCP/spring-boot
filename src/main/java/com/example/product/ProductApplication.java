package com.example.product;

import com.example.product.dto.ProductResponse;
import com.example.product.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);

	}

//	public void jsdjas(ProductResponse productResponse){
//		System.out.println(productResponse);
//	}
}
