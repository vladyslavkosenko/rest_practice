package com.example.rest_practice.entity;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Version
    private Long version;

}