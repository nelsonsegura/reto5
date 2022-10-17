package com.ciclo3.service;

import com.ciclo3.model.Category;
import com.ciclo3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return (List<Category>) categoryRepository.getAllCategory();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.getCategoryById(id);
    }

    public Category saveCategory(Category c) {
        if (c.getId() == null){
            return categoryRepository.saveCategory(c);
        }else{
            Optional<Category> ct = categoryRepository.getCategoryById(c.getId());
            if (ct.isEmpty()){
                return categoryRepository.saveCategory(c);
            }else{
                return c;
            }
        }
    }
    public Category updateCategory(Category category) {
        if (category.getId() != null) {
            Optional<Category> e = categoryRepository.getCategoryById(category.getId());
            if (!e.isEmpty()) {
                if (category.getDescription() != null) {
                    e.get().setDescription(category.getDescription());
                }
                if (category.getName() != null) {
                    e.get().setName(category.getName());
                }
                categoryRepository.saveCategory(e.get());
                return e.get();
            }
        }
        return category;
    }
    public boolean deleteCategory(Integer id){
        Boolean d = getCategoryById(id).map(category -> {
            categoryRepository.deleteCategory(category);
            return true;
        }).orElse(false);
        return d;
    }

}
