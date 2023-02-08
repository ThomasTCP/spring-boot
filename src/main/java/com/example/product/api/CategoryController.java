package com.example.product.api;

import com.example.product.entity.Category;
import com.example.product.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/category")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Category", description = "The Category API. Contains all the operations that can be performed on a category.")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public void createCategory(@RequestBody Category category){
        categoryService.addCategory(category);
    }

    @GetMapping(path = "/{id}")
    public Category readCategoryById(@PathVariable("id") Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public List<Category> readAllCategories(){
        return categoryService.getAllCategories();
    }

    @PutMapping(path = "/{id}")
    public void updateCategory(@PathVariable("id") long id, @RequestBody Category category){
        categoryService.updateCategory(id,category);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
    }
}
