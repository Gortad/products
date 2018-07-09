package org.product;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductTest {

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ProductController())
                .build();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .param("name","water")
                        .param("price", "3.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/1"))
                .andExpect(content().json("{'name': 'water', 'price': 3.0,'id': 1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetNotExistingProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products/2"))
                .andExpect(content().string(""))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products"))
                .andExpect(content().json("[{'name': 'water', 'price': 3.0,'id': 1}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetAfterInsertProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .param("name","cola")
                        .param("price", "6.0"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products"))
                .andExpect(content().json("[{'name': 'water', 'price': 3.0,'id': 1}, {'name': 'cola','price': 6.0,'id': 2}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetAfterDeleteProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/1"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/products"))
                .andDo(print())
                .andExpect(content().json("[]"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkGetAfterUpdateProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/1")
                        .param("name","water")
                        .param("price", "3.5"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/products/1"))
                .andExpect(content().json("{'name': 'water', 'price': 3.5,'id': 1}"))
                .andExpect(status().isOk());
    }

    @Test
    public void checkWrongParametersInsertProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/products")
                        .param("names","cola")
                        .param("price", "6.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void checkWrongParametersUpdateProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/products/1")
                        .param("name","cola")
                        .param("prrice", "6.0"))
                .andExpect(status().isBadRequest());
    }
}

