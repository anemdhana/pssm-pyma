package com.example.productapp;

import com.example.productapp.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void crudFlow_shouldWork() {
        // Create
        Product p = new Product(null, "Test Product", "desc", new BigDecimal("12.34"));
        ResponseEntity<Product> createResp = restTemplate.postForEntity("/api/products", p, Product.class);
        assertEquals(HttpStatus.CREATED, createResp.getStatusCode());
        assertNotNull(createResp.getBody());
        Long id = createResp.getBody().getId();
        assertNotNull(id);

        // List
        ResponseEntity<Product[]> listResp = restTemplate.getForEntity("/api/products", Product[].class);
        assertEquals(HttpStatus.OK, listResp.getStatusCode());
        assertTrue(listResp.getBody().length >= 1);

        // Get
        ResponseEntity<Product> getResp = restTemplate.getForEntity("/api/products/" + id, Product.class);
        assertEquals(HttpStatus.OK, getResp.getStatusCode());
        assertEquals("Test Product", getResp.getBody().getName());

        // Update
        Product updated = new Product(null, "Updated Product", "newdesc", new BigDecimal("20.00"));
        HttpEntity<Product> entity = new HttpEntity<>(updated);
        ResponseEntity<Product> updateResp = restTemplate.exchange("/api/products/" + id, HttpMethod.PUT, entity, Product.class);
        assertEquals(HttpStatus.OK, updateResp.getStatusCode());
        assertEquals("Updated Product", updateResp.getBody().getName());

        // Delete
        restTemplate.delete("/api/products/" + id);

        // After delete, getting the product should return 404
        try {
            restTemplate.getForEntity("/api/products/" + id, Product.class);
            fail("Expected 404 when fetching deleted product");
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
        }
    }
}
