package com.example.product.service;

import com.example.product.dto.CategoryDto;
import com.example.product.entity.Category;
import com.example.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    CategoryRepository categoryRepository;

    public void addCategory(CategoryDto input){
        Category category = new Category();
        category.setName(input.getName());
        categoryRepository.save(category);
    }

    public Optional<CategoryDto> getCategoryById(long id){
        Category category = categoryRepository.findById(id).get();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.loadFromEntity(category);
        return Optional.of(categoryDto);
    }

    public Optional<Category> getCategoryById2(long id){
        Category category = categoryRepository.findById(id).get();
        return Optional.of(category);
    }

    public List<CategoryDto> getAllCategories(){
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        for(Category c : categories){
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.loadFromEntity(c);
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    public void updateCategory(long id, CategoryDto category){
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }

}
