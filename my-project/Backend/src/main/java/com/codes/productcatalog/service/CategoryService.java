package com.codes.productcatalog.service;

import com.codes.productcatalog.model.Category;
import com.codes.productcatalog.model.Product;
import com.codes.productcatalog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public List<Category> getAllCategories(){
    return repo.findAll();
    }

//    public ResponseEntity<Product> getCategoryById(int id){
//        return new ResponseEntity<>(repo.findByCategoryId(id), HttpStatus.OK);
//    }
}
