package com.kubernetes.products.service;

import com.kubernetes.products.model.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }
}
