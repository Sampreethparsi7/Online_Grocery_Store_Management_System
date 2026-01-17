package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
