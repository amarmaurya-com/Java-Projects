package com.codes.productcatalog.repository;

import com.codes.productcatalog.model.Category;
import com.codes.productcatalog.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    Product findByCategoryId(int id);
}
