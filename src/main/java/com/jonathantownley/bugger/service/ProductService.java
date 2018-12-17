package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface ProductService {
    List<Product> findAll(String repositoryName);
    Product findById(String repositoryName, Long id);
    void update(String repositoryName, Product product);
    void delete(String repositoryName, Product product);
}
