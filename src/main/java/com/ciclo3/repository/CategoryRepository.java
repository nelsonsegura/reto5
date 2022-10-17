package com.ciclo3.repository;

import com.ciclo3.model.Category;
import com.ciclo3.repository.crud.CategoryCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository {
    @Autowired
    CategoryCrudRepository categoryCrudRepository;

    public List<Category> getAllCategory() {
        return (List<Category>) categoryCrudRepository.findAll();
    }

    public Optional<Category> getCategoryById(Integer id) {
        return categoryCrudRepository.findById(id);
    }

    public Category saveCategory(Category c) {
        return categoryCrudRepository.save(c);
    }
    public void deleteCategory(Category c){
        categoryCrudRepository.delete(c);
    }

}
