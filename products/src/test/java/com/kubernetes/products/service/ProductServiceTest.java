package com.kubernetes.products.service;

import com.kubernetes.products.model.CreateProduct;
import com.kubernetes.products.model.Product;
import com.kubernetes.products.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts() {
        List<Product> products = new ArrayList();
        products.add(new Product(1L, "Fruit", "Orange", 10.0));
        products.add(new Product(2L, "Fruit", "Apple", 12.0));
        when(productRepository.findAll()).thenReturn(products);
        List<Product> productsFromService = productService.findAll();

        assertEquals(products.size(), productsFromService.size());
    }

    @Test
    public void getAProduct() {
        Product product = new Product(1L, "Fruit", "Orange", 10.0);
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        Product productFromService = productService.getProduct(1L);

        assertEquals(productFromService.getId(), product.getId());
        assertEquals(productFromService.getPrice(), product.getPrice());
    }

    @Test
    public void createProduct() {
        CreateProduct product = new CreateProduct("Fruit", "Orange", 10.0);
        when(productRepository.save(any())).thenReturn(new Product(1L, product.getCategory(), product.getName(), product.getPrice()));
        Product createdProduct = productService.createProduct(product);

        assertEquals(createdProduct.getCategory(), product.getCategory());
        assertEquals(createdProduct.getName(), product.getName());
        assertEquals(createdProduct.getPrice(), product.getPrice());
    }
}
