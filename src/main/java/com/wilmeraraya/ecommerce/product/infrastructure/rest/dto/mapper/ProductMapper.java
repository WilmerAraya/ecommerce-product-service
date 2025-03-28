package com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.mapper;

import com.wilmeraraya.ecommerce.product.domain.model.Product;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.request.CreateProductRequest;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.request.UpdateProductRequest;
import com.wilmeraraya.ecommerce.product.infrastructure.rest.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice());
    }

    public Product toProduct(CreateProductRequest request) {
        return new Product(
                null,
                request.getName(),
                request.getDescription(),
                request.getPrice());
    }

    public void updateProductFromRequest(UpdateProductRequest request, Product product) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
    }
}