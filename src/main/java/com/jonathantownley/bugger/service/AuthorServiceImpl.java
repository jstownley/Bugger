package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorServiceImpl implements AuthorService{
    @Override
    public List<Author> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Author> authors = session.createQuery("select a from Author a").getResultList();

        // Close session
        session.close();
        return authors;
    }

    @Override
    public Author findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Author author = session.get(Author.class, id);

        // Close session
        session.close();
        return author;
    }

    @Override
    public void update(SessionFactory sessionFactory, Author author) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(author);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Author author) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
