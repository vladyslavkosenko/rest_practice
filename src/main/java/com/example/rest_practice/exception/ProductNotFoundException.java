package com.example.rest_practice.exception;

public class ProductNotFoundException  extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
