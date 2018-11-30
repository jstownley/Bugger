package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Product> products = session.createQuery("select p from Product p").getResultList();

        // Close session
        session.close();
        return products;
    }

    @Override
    public Product findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Product product = session.get(Product.class, id);

        // Close session
        session.close();
        return product;
    }

    @Override
    public void update(SessionFactory sessionFactory, Product product) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(product);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Product product) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
