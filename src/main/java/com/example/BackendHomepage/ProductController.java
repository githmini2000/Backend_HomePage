package com.example.BackendHomepage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private List<Product> allProducts;

    public ProductController() {
        loadProducts();
    }

    private void loadProducts() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("products.json");

            if (inputStream == null) {
                throw new RuntimeException("products.json file not found in resources folder!");
            }

            allProducts = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read products.json: " + e.getMessage());
        }
    }

    // Section 1: Fetch Paginated Products
    @GetMapping("/get-products")
    public List<Product> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {

        return paginateProducts(page, size);
    }

    // Section 2: Fetch Best-Selling Products (Paginated)
    @GetMapping("/get-best-selling-products")
    public List<Product> getBestSellingProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size) {

        return paginateProducts(page, size);
    }

    //Section 3
    @GetMapping("/get-section3-products")
    public List<Product> getSection3Products(@RequestParam(defaultValue = "4") int limit) {
        return allProducts.stream().limit(limit).collect(Collectors.toList());
    }
    private List<Product> paginateProducts(int page, int size) {
        if (allProducts == null || allProducts.isEmpty()) {
            return List.of();
        }

        int start = page * size;
        int end = Math.min(start + size, allProducts.size());

        if (start >= allProducts.size()) {
            return List.of();
        }

        return allProducts.subList(start, end);
    }

    private List<Product> fetchLimitedProducts(int limit) {
        if (allProducts == null || allProducts.isEmpty()) {
            return List.of();
        }

        return allProducts.stream().limit(limit).collect(Collectors.toList());
    }
}
