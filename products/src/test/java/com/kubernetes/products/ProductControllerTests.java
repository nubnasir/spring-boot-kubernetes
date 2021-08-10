package com.kubernetes.products;

import com.kubernetes.products.model.ProductDto;
import com.kubernetes.products.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class ProductControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    public void getAllProducts() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(new ProductDto(1L, "Bread", 100, 0));
        productDtos.add(new ProductDto(2L, "Banana", 100, 0));
        Mockito.when(productService.getAllProducts()).thenReturn(productDtos);
        mockMvc.perform(MockMvcRequestBuilders.get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(2))).andDo(print());
    }
}
