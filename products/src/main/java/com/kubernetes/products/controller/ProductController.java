package com.kubernetes.products.controller;

import com.kubernetes.products.model.CreateProduct;
import com.kubernetes.products.model.Product;
import com.kubernetes.products.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public boolean createProduct(@RequestBody CreateProduct product) {
        return productService.createProduct(product) != null;
    }
}
