package com.codes.productcatalog.controller;

import com.codes.productcatalog.model.Category;
import com.codes.productcatalog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5174/")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = service.getAllCategories();
        if(categories.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categories);
    }
}
