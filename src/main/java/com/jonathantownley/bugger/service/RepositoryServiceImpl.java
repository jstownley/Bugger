package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RepositoryServiceImpl implements RepositoryService {
    
    public Long save(SessionFactory sessionFactory, Repository repository){
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        Long id = (Long) session.save(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();

        return id;
    }

    public List<Repository> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Get repositories
        List<Repository> repositories = session.createQuery("from Repository r").list();

        // Close session
        session.close();

        return repositories;
    }

    public Repository findById(SessionFactory sessionFactory, Long id) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Retrieve a persistent object
        Repository repository = session.get(Repository.class, id);

        // Close session
        session.close();

        return repository;
    }

    public void update(SessionFactory sessionFactory, Repository repository) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        session.saveOrUpdate(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    public void delete(SessionFactory sessionFactory, Repository repository) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin transaction
        session.beginTransaction();

        // Use the session to save the data
        session.delete(repository);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }
}
