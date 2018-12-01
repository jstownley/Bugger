package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Severity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class SeverityServiceImpl implements SeverityService {
    @Override
    public List<Severity> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Severity> severityes = session.createQuery("select s from Severity s").getResultList();

        // Close session
        session.close();
        return severityes;
    }

    @Override
    public Severity findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Severity severity = session.get(Severity.class, id);

        // Close session
        session.close();
        return severity;
    }

    @Override
    public void update(SessionFactory sessionFactory, Severity severity) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(severity);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Severity severity) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(severity);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
