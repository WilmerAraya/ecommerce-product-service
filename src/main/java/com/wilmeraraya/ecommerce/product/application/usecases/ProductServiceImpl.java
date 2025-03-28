package com.wilmeraraya.ecommerce.product.application.usecases;

import com.wilmeraraya.ecommerce.product.domain.model.Product;
import com.wilmeraraya.ecommerce.product.domain.port.input.ProductService;
import com.wilmeraraya.ecommerce.product.domain.port.output.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found");
        }
        return products;
    }

    @Transactional(readOnly = true)
    @Override
    public Product getProductById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product not found with id: %d", id)));
    }

    @Override
    public Product addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getId() != null && productRepository.findById(product.getId()).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Product already exists with id: %d", product.getId()));
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product newProduct) {
        if (newProduct == null || newProduct.getId() == null) {
            throw new IllegalArgumentException("Product and ID cannot be null for update");
        }

        Product existingProduct = productRepository.findById(newProduct.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Product not found with id: %d", newProduct.getId())));

        existingProduct.update(newProduct);
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (productRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException(
                    String.format("Product not found with id: %d", id));
        }
        productRepository.delete(id);
    }
}