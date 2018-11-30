package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BugServiceImpl implements BugService {
    @Override
    public List<Bug> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Bug> bugs = session.createQuery("select s from Bug s").getResultList();

        // Close session
        session.close();
        return bugs;
    }

    @Override
    public Bug findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Bug bug = session.get(Bug.class, id);

        // Close session
        session.close();
        return bug;
    }

    @Override
    public Author findAuthorByBugId(SessionFactory sessionFactory, Long id) {
        return findById(sessionFactory, id).getAuthor();
    }

    @Override
    public List<Note> findNotesByBugId(SessionFactory sessionFactory, Long id) {
        return findById(sessionFactory, id).getNotes();
    }

    @Override
    public Product findProductByBugId(SessionFactory sessionFactory, Long id) {
        return findById(sessionFactory, id).getProduct();
    }

    @Override
    public Stage findStageByBugId(SessionFactory sessionFactory, Long id) {
        return findById(sessionFactory, id).getStage();
    }

    @Override
    public void update(SessionFactory sessionFactory, Bug bug) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(bug);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Bug bug) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(bug);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
