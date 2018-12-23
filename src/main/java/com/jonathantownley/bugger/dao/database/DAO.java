package com.jonathantownley.bugger.dao.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

public abstract class DAO {

    @Autowired
    private Map<String, SessionFactory> sessionFactories;

    @PersistenceContext
    private EntityManager entityManager;

    abstract public List findAll(String repoName);

    abstract public Object findById(String repoName, Long id);

    public void update(String repoName, Object object) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    public void delete(String repoName, Object object) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(object);
        session.getTransaction().commit();

        // Close session
        session.close();
    }


}
