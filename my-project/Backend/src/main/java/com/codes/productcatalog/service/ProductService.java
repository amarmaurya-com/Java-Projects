package com.codes.productcatalog.service;

import com.codes.productcatalog.model.Product;
import com.codes.productcatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;

    public List<Product> getAllProducts(){
        return repo.findAll();
    }

    public List<Product> getProductByCategoryId(Long id){
        return repo.findByCategoryId(id);
    }
}
