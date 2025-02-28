package com.example.BackendHomepage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @GetMapping("/get-products")
    public List<Product> getProducts() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read JSON from resources folder
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("products.json");

            if (inputStream == null) {
                throw new RuntimeException("products.json file not found in resources folder!");
            }

            return objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            e.printStackTrace(); // Print error details to console
            throw new RuntimeException("Failed to read products.json: " + e.getMessage());
        }
    }
}