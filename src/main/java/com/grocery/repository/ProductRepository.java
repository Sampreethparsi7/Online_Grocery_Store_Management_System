package com.grocery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByActiveTrue();
	List<Product> findByAvailableStockLessThan(int threshold);

}
