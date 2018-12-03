package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Product;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface BugService {
    List<Bug> findAll(Repository repository);
    Bug findById(Repository repository, Long id);
    List<Note> findNotesByBugId(Repository repository, Long id);
    Product findProductByBugId(Repository repository, Long id);
    void update(Repository repository, Bug bug);
    void delete(Repository repository, Bug bug);
}
