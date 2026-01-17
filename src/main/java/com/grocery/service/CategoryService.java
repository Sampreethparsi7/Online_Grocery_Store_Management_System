package com.grocery.service;

import java.util.List;

import com.grocery.entity.Category;

public interface CategoryService {
	Category addCategory(Category category);
	
	List<Category> getAllCategories();

}
