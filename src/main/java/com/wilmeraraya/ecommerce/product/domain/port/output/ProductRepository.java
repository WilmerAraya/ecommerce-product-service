package com.wilmeraraya.ecommerce.product.domain.port.output;

import com.wilmeraraya.ecommerce.product.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void delete(Long id);
}