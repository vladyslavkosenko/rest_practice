package com.example.rest_practice.controller;

import com.example.rest_practice.entity.Product;
import com.example.rest_practice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getProduct() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Product with id " + id + " is missed"));
    }

    @PostMapping
    public Product post(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{productId}")
    public Product update(@PathVariable Long productId, @RequestBody Product product) {
//        if (!id.equals(product.getId())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product id in body must be " + id);
//        }
        return productService.putProduct(productId, product);
    }

    @DeleteMapping("/{productID}")
    public void delete(@PathVariable Long productID) {
        productService.deleteProduct(productID);
    }

}



