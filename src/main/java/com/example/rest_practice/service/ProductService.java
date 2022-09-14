package com.example.rest_practice.service;

import com.example.rest_practice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;
}
