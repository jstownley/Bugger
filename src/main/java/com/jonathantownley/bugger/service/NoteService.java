package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Note;
import org.hibernate.SessionFactory;

import java.util.List;

public interface NoteService {
    List<Note> findAll(SessionFactory sessionFactory);
    Note findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Note note);
    void delete(SessionFactory sessionFactory, Note note);
}
