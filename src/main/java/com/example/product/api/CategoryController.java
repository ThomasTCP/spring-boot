package com.example.product.api;

import com.example.product.dto.CategoryDto;
import com.example.product.entity.Category;
import com.example.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody CategoryDto input){
        categoryService.addCategory(input);
    }

    @GetMapping(path = "/{id}")
    public Optional<Category> readCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<CategoryDto> readAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping(path = "/{id}")
    public void updateCategory(@PathVariable("id") long id, @RequestBody CategoryDto input){
        categoryService.updateCategory(id,input);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
    }
}
