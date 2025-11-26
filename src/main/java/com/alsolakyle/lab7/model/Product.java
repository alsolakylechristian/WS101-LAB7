package com.alsolakyle.lab7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, etc. [cite: 238]
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;     // Unique identifier [cite: 223]
    private String name; // Name of the product [cite: 223]
    private Double price; // Price of the product [cite: 223]
}