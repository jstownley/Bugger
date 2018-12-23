package com.jonathantownley.bugger.dao.database;

import com.jonathantownley.bugger.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProductDao extends DAO {

    @Autowired
    private Map<String, SessionFactory> sessionFactories;

    @Override
    public List<Product> findAll(String repoName) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        List<Product> products = session.createQuery("select n from Product n").getResultList();

        // Close session
        session.close();
        return products;
    }

    @Override
    public Product findById(String repoName, Long id) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        Product product = session.get(Product.class, id);

        // Close session
        session.close();
        return product;
    }
}
