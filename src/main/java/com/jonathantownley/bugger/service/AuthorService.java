package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Author;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface AuthorService {
    List<Author> findAll(Repository repository);
    Author findById(Repository repository, Long id);
    void update(Repository repository, Author author);
    void delete(Repository repository, Author author);
}
