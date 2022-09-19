package com.example.rest_practice.service;

import com.example.rest_practice.entity.Product;
import com.example.rest_practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long productId) {
        isFound(productId);
        return productRepository.findById(productId).orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product putProduct(Product productWithId) {
        return productRepository.save(productWithId);
    }

    public void deleteProduct(Long productId) {
        isFound(productId);
        productRepository.deleteById(productId);
    }

    public void isFound(Long productId)  {
        if (!productRepository.existsById(productId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}