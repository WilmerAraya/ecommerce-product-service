package com.wilmeraraya.ecommerce.product.infrastructure.rest.controllers;

import com.wilmeraraya.ecommerce.product.domain.model.Product;
import com.wilmeraraya.ecommerce.product.domain.port.input.ProductService;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.mapper.ProductMapper;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.request.CreateProductRequest;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.request.UpdateProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        logger.info("Request to get all products");
        List<Product> products = productService.getAllProducts();
        logger.debug("Retrieved {} products", products.size());
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        logger.info("Request to get product with id: {}", id);
        Product product = productService.getProductById(id);
        logger.debug("Retrieved product with id: {}", product.getId());
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductRequest request) {
        logger.info("Request to create new product");
        Product product = productMapper.toProduct(request);
        Product createdProduct = productService.addProduct(product);
        logger.info("Created product with id: {}", createdProduct.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        logger.info("Request to update product with id: {}", id);
        Product product = productService.getProductById(id);
        productMapper.updateProductFromRequest(request, product);
        Product updatedProduct = productService.updateProduct(product);
        logger.info("Updated product with id: {}", updatedProduct.getId());
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Request to delete product with id: {}", id);
        productService.deleteProduct(id);
        logger.info("Deleted product with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}