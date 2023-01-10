package com.example.product.api;

import com.example.product.model.Category;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/category")
@RestController
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public void createCategory(@RequestBody Category input){
        categoryService.addCategory(input);
    }

    @GetMapping(path = "/{id}")
    public Optional<Category> readCategoryById(@PathVariable("id") long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<Category> readAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping(path = "/{id}")
    public void updateCategory(@PathVariable("id") long id, @RequestBody Category input){
        categoryService.updateCategory(id,input);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
    }
}
