package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Session;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAll(Repository repository) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        List<Product> products = session.createQuery("select p from Product p").getResultList();

        // Close session
        session.close();
        return products;
    }

    @Override
    public Product findById(Repository repository, Long id) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        Product product = session.get(Product.class, id);

        // Close session
        session.close();
        return product;
    }

    @Override
    public void update(Repository repository, Product product) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Repository repository, Product product) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
