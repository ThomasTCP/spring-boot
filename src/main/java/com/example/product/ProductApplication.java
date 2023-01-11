package com.example.product;

import com.example.product.entity.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
		Category category = new Category();
		System.out.println(category.getId());
		Category category1 = new Category();
		System.out.println(category1.getId());
	}

}
