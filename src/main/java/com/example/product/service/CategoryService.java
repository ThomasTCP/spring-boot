package com.example.product.service;

import com.example.product.entity.Category;
import com.example.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@EnableJpaRepositories(basePackages = "com.example.product.repository")
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category input){
        Category category = new Category();
        category.setName(input.getName());
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Long id, Category category){
        Category category1 = categoryRepository.findById(id).get();
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
