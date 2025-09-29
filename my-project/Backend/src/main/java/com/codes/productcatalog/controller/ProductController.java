package com.codes.productcatalog.controller;

import com.codes.productcatalog.model.Product;
import com.codes.productcatalog.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174/")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = service.getAllProducts();
        if (products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("category/{id}")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable("id") Long id){
        List<Product> products = service.getProductByCategoryId(id);
        if (products.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(products);
    }
}
