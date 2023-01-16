package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private String name;
    private Long category;
    private String price;
    private Integer quantity;
    private String time_create;
    private String brand;
    private String description;
}
