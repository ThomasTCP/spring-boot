package com.example.product.service;

import com.example.product.model.Category;
import com.example.product.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    CategoryRepository categoryRepository;

    public void addCategory(Category input){
        categoryRepository.save(input);
    }

    public Optional<Category> getCategoryById(long id){
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(long id, Category category){
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public void deleteCategory(long id){
        categoryRepository.deleteById(id);
    }
}
