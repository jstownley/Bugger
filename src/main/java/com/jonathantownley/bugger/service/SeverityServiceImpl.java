package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.model.Severity;
import org.hibernate.Session;

import java.util.List;

public class SeverityServiceImpl implements SeverityService {
    @Override
    public List<Severity> findAll(Repository repository) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        List<Severity> severityes = session.createQuery("select s from Severity s").getResultList();

        // Close session
        session.close();
        return severityes;
    }

    @Override
    public Severity findById(Repository repository, Long id) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        Severity severity = session.get(Severity.class, id);

        // Close session
        session.close();
        return severity;
    }

    @Override
    public void update(Repository repository, Severity severity) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(severity);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Repository repository, Severity severity) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(severity);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
