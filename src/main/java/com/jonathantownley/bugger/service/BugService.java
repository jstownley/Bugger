package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.*;

import java.util.List;

public interface BugService {
    List<Bug> findAll(Repository repository);
    Bug findById(Repository repository, Long id);
    Author findAuthorByBugId(Repository repository, Long id);
    List<Note> findNotesByBugId(Repository repository, Long id);
    Product findProductByBugId(Repository repository, Long id);
    Status findStageByBugId(Repository repository, Long id);
    void update(Repository repository, Bug bug);
    void delete(Repository repository, Bug bug);
}
