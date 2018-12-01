package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface ProductService {
    List<Product> findAll(Repository repository);
    Product findById(Repository repository, Long id);
    void update(Repository repository, Product product);
    void delete(Repository repository, Product product);
}
