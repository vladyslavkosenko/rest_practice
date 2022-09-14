package com.example.rest_practice.controller;

import com.example.rest_practice.entity.Product;
import com.example.rest_practice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @GetMapping
    public List<Product> products(Pageable pageable) {
        return productRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id " + id + " is missed"));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id field must be null");
        }
        return productRepository.save(product);
    }

    @PostMapping("/alternative")
    public ResponseEntity<Product> alternativeCreateProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        Product saved = productRepository.save(product);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!id.equals(product.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product id in body must be " + id);
        }
        return productRepository.save(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        /*
        if (productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id " + id + " is missed");
        }*/
        productRepository.deleteById(id);
    }

}

