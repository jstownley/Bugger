package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface BugService {
    List<Bug> findAll(String repositoryName);
    Bug findById(String repositoryName, Long id);
    List<Note> findNotesByBugId(String repositoryName, Long id);
    Product findProductByBugId(String repositoryName, Long id);
    void update(String repositoryName, Bug bug);
    void delete(String repositoryName, Bug bug);
}
