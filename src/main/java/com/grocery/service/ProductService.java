package com.grocery.service;

import java.util.List;

import com.grocery.entity.Product;

public interface ProductService {
	Product addProduct(Product product);
    Product updateProduct(Long productId, Product product);
    List<Product> getAllActiveProducts();
    List<Product> getLowStockProducts();
    Product getProductById(Long productId);

}
