package com.sheryians.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.repository.CategoryRepository;
import com.sheryians.major.model.Category;

import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category Categorydata) {
        categoryRepository.save(Categorydata);
    }

    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

}
