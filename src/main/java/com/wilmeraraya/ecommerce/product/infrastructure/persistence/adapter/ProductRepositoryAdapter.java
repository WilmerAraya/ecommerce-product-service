package com.wilmeraraya.ecommerce.product.infrastructure.persistence.adapter;

import com.wilmeraraya.ecommerce.product.domain.model.Product;
import com.wilmeraraya.ecommerce.product.domain.port.output.ProductRepository;
import com.wilmeraraya.ecommerce.product.infrastructure.persistence.entity.ProductEntity;
import com.wilmeraraya.ecommerce.product.infrastructure.persistence.repository.JpaProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {
    private final JpaProductRepository jpaRepo;

    @Override
    public List<Product> findAll() {
        return jpaRepo.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepo.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = toEntity(product);
        ProductEntity savedEntity = jpaRepo.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void delete(Long id) {
        jpaRepo.deleteById(id);
    }

    private ProductEntity toEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        return entity;
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice());
    }
}