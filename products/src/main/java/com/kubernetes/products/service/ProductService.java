package com.kubernetes.products.service;

import com.kubernetes.products.model.CreateProduct;
import com.kubernetes.products.model.Product;
import com.kubernetes.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    public Product createProduct(CreateProduct product) {
        Product persistsProduct = new Product(product.getCategory(), product.getName(), product.getPrice());
        productRepository.save(persistsProduct);
        return persistsProduct;
    }
}
