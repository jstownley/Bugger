package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.dao.ProductDao;
import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(String repositoryName) {
        return productDao.findAll(repositoryName);
    }

    @Override
    public Product findById(String repositoryName, Long id) {
        return productDao.findById(repositoryName, id);
    }

    @Override
    public void update(String repositoryName, Product product) {
        productDao.update(repositoryName, product);
    }

    @Override
    public void delete(String repositoryName, Product product) {
        productDao.delete(repositoryName, product);
    }
}
