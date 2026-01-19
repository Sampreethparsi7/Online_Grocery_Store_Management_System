package com.grocery.services;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.grocery.entity.Product;
import com.grocery.exception.ResourceNotFoundException;
import com.grocery.repository.ProductRepository;
import com.grocery.service.ProductServiceImpl;

class ProductServiceTest {

    @Mock

    private ProductRepository productRepository;

    @InjectMocks

    private ProductServiceImpl productService;

    @BeforeEach

    void setUp() {

        MockitoAnnotations.openMocks(this);

    }

    // ---------------- ADD PRODUCT ----------------

    @Test

    void addProduct_shouldSetAvailableStockAndActive() {

        Product product = new Product();

        product.setTotalStock(50);

        when(productRepository.save(any(Product.class)))

                .thenAnswer(invocation -> invocation.getArgument(0));

        Product saved = productService.addProduct(product);

        assertEquals(50, saved.getAvailableStock());

        assertTrue(saved.isActive());

    }

    // ---------------- UPDATE PRODUCT ----------------

    @Test

    void updateProduct_shouldUpdateFields() {

        Product existing = new Product();

        existing.setProductName("Old");

        Product updated = new Product();

        updated.setProductName("New");

        updated.setTotalStock(20);

        when(productRepository.findById(1L))

                .thenReturn(Optional.of(existing));

        when(productRepository.save(any(Product.class)))

                .thenReturn(existing);

        Product result = productService.updateProduct(1L, updated);

        assertEquals("New", result.getProductName());

        assertEquals(20, result.getAvailableStock());

    }

    @Test

    void updateProduct_shouldThrowException_whenNotFound() {

        when(productRepository.findById(1L))

                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,

                () -> productService.updateProduct(1L, new Product()));

    }

    // ---------------- GET PRODUCT ----------------

    @Test

    void getProductById_shouldReturnProduct() {

        Product product = new Product();

        when(productRepository.findById(1L))

                .thenReturn(Optional.of(product));

        Product result = productService.getProductById(1L);

        assertNotNull(result);

    }

    @Test

    void getProductById_shouldThrowException_whenNotFound() {

        when(productRepository.findById(1L))

                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,

                () -> productService.getProductById(1L));

    }

    // ---------------- GET LISTS ----------------

    @Test

    void getAllActiveProducts_shouldReturnList() {

        when(productRepository.findByActiveTrue())

                .thenReturn(List.of(new Product(), new Product()));

        List<Product> products = productService.getAllActiveProducts();

        assertEquals(2, products.size());

    }

    @Test

    void getLowStockProducts_shouldReturnList() {

        when(productRepository.findByAvailableStockLessThan(5))

                .thenReturn(List.of(new Product()));

        List<Product> products = productService.getLowStockProducts();

        assertEquals(1, products.size());

    }

}