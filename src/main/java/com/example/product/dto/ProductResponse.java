package com.example.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponse {
    private String name;
    private String price;
    private Integer quantity;
    private String date;
    private Long category_id;
    private String brand;
    private String description;
}
