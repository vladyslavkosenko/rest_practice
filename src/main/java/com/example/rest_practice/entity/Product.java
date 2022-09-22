package com.example.rest_practice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Version
    private Long version;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}