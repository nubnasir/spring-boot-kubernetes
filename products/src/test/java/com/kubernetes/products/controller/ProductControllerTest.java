package com.kubernetes.products.controller;

import com.kubernetes.products.model.CreateProduct;
import com.kubernetes.products.model.Product;
import com.kubernetes.products.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends BaseTestController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testAllProducts() throws Exception {
        List<Product> products = new ArrayList();
        products.add(new Product(1L, "Fruit", "Orange", 10.0));
        products.add(new Product(1L, "Fruit", "Apple", 12.0));
        when(productService.findAll()).thenReturn(products);
        mockMvc.perform(MockMvcRequestBuilders.get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    public void testAProduct() throws Exception {
        Product product = new Product(1L, "Fruit", "Orange", 10.0);
        when(productService.getProduct(Mockito.any())).thenReturn(product);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.category", is("Fruit")))
                .andExpect(jsonPath("$.name", is("Orange")))
                .andExpect(jsonPath("$.price", is(10.0)));
    }

    @Test
    public void createProduct() throws Exception {
        CreateProduct product = new CreateProduct("Fruit", "Apple", 15.0);
        when(productService.createProduct(any())).thenReturn(new Product(1L, product.getCategory(), product.getName(), product.getPrice()));
        mockMvc.perform(MockMvcRequestBuilders.post("/products").content(asJsonString(product)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
