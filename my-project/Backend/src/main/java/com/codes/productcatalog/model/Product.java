package com.codes.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double price=25.0;

    @ManyToOne
    @JoinColumn(name =  "category_id", nullable = false)
    @JsonBackReference
    private Category category;
}
