package com.grocery.service;

import java.util.List;

import com.grocery.entity.Product;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.ProductRepository;

public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        product.setAvailableStock(product.getTotalStock());
        product.setActive(true);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setMrp(product.getMrp());
        existingProduct.setSellingPrice(product.getSellingPrice());
        existingProduct.setDiscountAmount(product.getDiscountAmount());
        existingProduct.setTotalStock(product.getTotalStock());
        existingProduct.setLowStockThreshold(product.getLowStockThreshold());
        existingProduct.setSponsored(product.isSponsored());
        existingProduct.setTodaySpecial(product.isTodaySpecial());
        existingProduct.setActive(product.isActive());
        existingProduct.setAvailableStock(product.getTotalStock());
        return productRepository.save(existingProduct);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findByActiveTrue();
    }

    @Override
    public List<Product> getLowStockProducts() {
        return productRepository.findByAvailableStockLessThan(5);
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

    }

}
