package com.example.product.dto;

import com.example.product.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDto {
    private String name;

    public CategoryDto(@JsonProperty String name) {
        this.name = name;
    }

    public CategoryDto() {
    }

    public void loadFromEntity(Category category) {
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }
}
