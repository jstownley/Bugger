package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.*;
import org.hibernate.SessionFactory;

import java.util.List;

public interface BugService {
    List<Bug> findAll(SessionFactory sessionFactory);
    Bug findById(SessionFactory sessionFactory, Long id);
    Author findAuthorByBugId(SessionFactory sessionFactory, Long id);
    List<Note> findNotesByBugId(SessionFactory sessionFactory, Long id);
    Product findProductByBugId(SessionFactory sessionFactory, Long id);
    Status findStageByBugId(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Bug bug);
    void delete(SessionFactory sessionFactory, Bug bug);
}
