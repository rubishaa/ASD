package com.track.expensetrackerapp.service;

import com.track.expensetrackerapp.Repository.CategoryRepository;
import com.track.expensetrackerapp.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategoryByType(String type) {
        return categoryRepository.findAllByCategoryType(type);
    }

    public Category findCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }
        return null;

    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
