package com.alsolakyle.lab7.service;

import com.alsolakyle.lab7.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // In-memory database
    private final List<Product> productList = new ArrayList<>();

    public ProductService() {
        productList.add(new Product(1L, "Laptop Pro", 1200.00));
        productList.add(new Product(2L, "Smartphone X", 800.00));
        productList.add(new Product(3L, "Wireless Earbuds", 150.00));
    }

    public List<Product> findAll() {
        return productList;
    }

    public Optional<Product> findById(Long id) {
        // Stream the list to find a product matching the ID
        return productList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Product save(Product product) {
        Long newId = productList.isEmpty() ? 1L : productList.get(productList.size() - 1).getId() + 1;
        product.setId(newId);
        productList.add(product);
        return product;
    }

    public boolean delete(Long id) {
        return productList.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Product> update(Long id, Product updatedDetails) {
        Optional<Product> existingProduct = findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedDetails.getName());
            product.setPrice(updatedDetails.getPrice());
            return Optional.of(product);
        }
        return Optional.empty();
    }
}
