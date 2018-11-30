package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;
import org.hibernate.SessionFactory;

import java.util.List;

public interface RepositoryService {
    List<Repository> findAll(SessionFactory sessionFactory);
    Repository findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Repository repository);
    void delete(SessionFactory sessionFactory, Repository repository);
}
