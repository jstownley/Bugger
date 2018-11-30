package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Product;
import org.hibernate.SessionFactory;

import java.util.List;

public interface ProductService {
    List<Product> findAll(SessionFactory sessionFactory);
    Product findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Product product);
    void delete(SessionFactory sessionFactory, Product product);
}
