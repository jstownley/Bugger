package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Author;
import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Session;

import java.util.List;

public class AuthorServiceImpl implements AuthorService{
    @Override
    public List<Author> findAll(Repository repository) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        List<Author> authors = session.createQuery("select a from Author a").getResultList();

        // Close session
        session.close();
        return authors;
    }

    @Override
    public Author findById(Repository repository, Long id) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        Author author = session.get(Author.class, id);

        // Close session
        session.close();
        return author;
    }

    @Override
    public void update(Repository repository, Author author) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(author);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Repository repository, Author author) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(author);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
