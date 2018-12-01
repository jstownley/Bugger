package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Repository;
import org.hibernate.Session;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Override
    public List<Note> findAll(Repository repository) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        List<Note> notes = session.createQuery("select n from Note n").getResultList();

        // Close session
        session.close();
        return notes;
    }

    @Override
    public Note findById(Repository repository, Long id) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        Note note = session.get(Note.class, id);

        // Close session
        session.close();
        return note;
    }

    @Override
    public void update(Repository repository, Note note) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(note);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(Repository repository, Note note) {
        // Open session
        Session session = repository.getSessionFactory().openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(note);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
