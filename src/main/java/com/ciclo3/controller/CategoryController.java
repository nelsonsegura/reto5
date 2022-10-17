package com.ciclo3.controller;
import com.ciclo3.model.Category;
import com.ciclo3.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Category")
@CrossOrigin(origins = "*")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Category saveCategory(@RequestBody Category c) {
        return categoryService.saveCategory(c);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Category updateCategory(@RequestBody Category c) {
        return categoryService.updateCategory(c);
    };

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}