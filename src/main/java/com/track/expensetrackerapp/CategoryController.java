package com.track.expensetrackerapp.controller;

import com.track.expensetrackerapp.model.Category;
import com.track.expensetrackerapp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(value = "/type/{type}", produces = "application/json")
    ResponseEntity<List<Category>> getAllCategoryByType(@PathVariable String type) {
        List<Category> categoryList = categoryService.findAllCategoryByType(type);
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<Category> findCategoryById(@RequestParam Integer id) {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping
    ResponseEntity<Category> deleteCategoryById(@RequestParam Integer id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    ResponseEntity<?> createTransaction(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
    }

}
