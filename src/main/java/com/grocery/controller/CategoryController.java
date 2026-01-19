package com.grocery.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Category;
import com.grocery.service.CategoryService;

@RestController

@RequestMapping("/api/categories")
public class CategoryController {
	 private final CategoryService categoryService;

	    public CategoryController(CategoryService categoryService) {

	        this.categoryService = categoryService;

	    }

	    @PostMapping("/add")
	    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
	        Category savedCategory = categoryService.addCategory(category);
	        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	    }

	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories() {
	        return ResponseEntity.ok(categoryService.getAllCategories());
	    }

}
