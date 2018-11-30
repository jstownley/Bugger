package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Author;
import org.hibernate.SessionFactory;

import java.util.List;

public interface AuthorService {
    List<Author> findAll(SessionFactory sessionFactory);
    Author findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Author author);
    void delete(SessionFactory sessionFactory, Author author);
}
