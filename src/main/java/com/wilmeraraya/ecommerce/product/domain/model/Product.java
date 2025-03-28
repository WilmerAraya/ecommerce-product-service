package com.wilmeraraya.ecommerce.product.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public Product(Long id, String name, String description, Double price) {
        setId(id);
        setName(name);
        setDescription(description);
        setPrice(price);
    }

    public void setId(Long id) {
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Product description cannot be null");
        }
        this.description = description.trim();
    }

    public void setPrice(Double price) {
        if (price == null) {
            throw new IllegalArgumentException("Product price cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        this.price = price;
    }

    public void update(Product other) {
        if (other == null) {
            throw new IllegalArgumentException("Update product cannot be null");
        }
        setName(other.getName());
        setDescription(other.getDescription());
        setPrice(other.getPrice());
    }
}