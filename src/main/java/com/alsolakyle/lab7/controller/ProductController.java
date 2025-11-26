package com.alsolakyle.lab7.controller;

import com.alsolakyle.lab7.service.ProductService;
import com.alsolakyle.lab7.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Stereotype annotation for the controller [cite: 250]
@RequestMapping("/api/products") // Base path [cite: 251]
public class ProductController {

    private final ProductService productService;

    // Constructor Injection [cite: 252]
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 1. READ ALL - Return 200 OK [cite: 255, 263]
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    // 2. READ ONE - Return 200 OK or 404 Not Found [cite: 255, 268]
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3. CREATE - Return 201 Created [cite: 255, 273]
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    // 4. UPDATE - Return 200 OK or 404 Not Found [cite: 255, 278]
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> updatedProduct = productService.update(id, productDetails);

        if (updatedProduct.isPresent()) {
            return new ResponseEntity<>(updatedProduct.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 5. DELETE - Return 200 OK (or 204 No Content) [cite: 255]
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 is standard for delete
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}