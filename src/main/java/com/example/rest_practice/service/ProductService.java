package com.example.rest_practice.service;

import com.example.rest_practice.entity.Product;
import com.example.rest_practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


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

    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product putProduct(Product productWithId) {
//        if (!productId.equals(productToUpdate.getId())) {
//        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product productId in body must be " + productId);
//    }
        return productRepository.save(productWithId);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + productId + " is missed");
        }
        productRepository.deleteById(productId);

    }

}