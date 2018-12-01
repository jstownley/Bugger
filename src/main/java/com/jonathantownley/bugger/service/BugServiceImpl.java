package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.*;
import org.hibernate.Session;

import java.util.List;

public class BugServiceImpl implements BugService {
    @Override
    public List<Bug> findAll(Repository repository) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        List<Bug> bugs = session.createQuery("select s from Bug s").getResultList();

        // Close session
        session.close();
        return bugs;
    }

    @Override
    public Bug findById(Repository repository, Long id) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        Bug bug = session.get(Bug.class, id);

        // Close session
        session.close();
        return bug;
    }

    @Override
    public Author findAuthorByBugId(Repository repository, Long id) {
        return findById(repository, id).getAuthor();
    }

    @Override
    public List<Note> findNotesByBugId(Repository repository, Long id) {
        return findById(repository, id).getNotes();
    }

    @Override
    public Product findProductByBugId(Repository repository, Long id) {
        return findById(repository, id).getProduct();
    }

    @Override
    public Status findStageByBugId(Repository repository, Long id) {
        return findById(repository, id).getStatus();
    }

    @Override
    public void update(Repository repository, Bug bug) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(bug);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Repository repository, Bug bug) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(bug);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
