package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StatusServiceImpl implements StatusService {
    @Override
    public List<Status> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Status> statuses = session.createQuery("select s from Status s").getResultList();

        // Close session
        session.close();
        return statuses;
    }

    @Override
    public Status findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Status status = session.get(Status.class, id);

        // Close session
        session.close();
        return status;
    }

    @Override
    public void update(SessionFactory sessionFactory, Status status) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(status);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Status status) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(status);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
