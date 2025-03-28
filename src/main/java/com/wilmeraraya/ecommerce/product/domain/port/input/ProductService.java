package com.wilmeraraya.ecommerce.product.domain.port.input;

import com.wilmeraraya.ecommerce.product.domain.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
