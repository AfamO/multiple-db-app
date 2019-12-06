package com.afam.app.multipledb.dao.product;

import com.afam.app.multipledb.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
