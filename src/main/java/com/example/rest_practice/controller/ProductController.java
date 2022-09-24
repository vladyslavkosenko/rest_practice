package com.example.rest_practice.controller;

import com.example.rest_practice.entity.Product;
import com.example.rest_practice.exception.ProductNotFoundException;
import com.example.rest_practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        var list = productService.getProducts();
        String version = String.valueOf(list.hashCode());
        return ResponseEntity.ok()
                .eTag(version)
                .body(list);
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.findById(productId);
        String version = Long.toString(product.getVersion());
        return ResponseEntity.ok()
                .eTag(version)
                .body(product);
    }

    @PostMapping
    public Product post(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping
    public Product update(@RequestBody Product productWithId) {
        return productService.putProduct(productWithId);
    }

    @DeleteMapping("/{productID}")
    public void delete(@PathVariable Long productID) throws ProductNotFoundException {
        productService.deleteProduct(productID);
    }
}



