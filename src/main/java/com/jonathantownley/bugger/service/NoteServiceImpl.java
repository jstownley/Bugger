package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    @Override
    public List<Note> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Note> notes = session.createQuery("select n from Note n").getResultList();

        // Close session
        session.close();
        return notes;
    }

    @Override
    public Note findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Note note = session.get(Note.class, id);

        // Close session
        session.close();
        return note;
    }

    @Override
    public void update(SessionFactory sessionFactory, Note note) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(note);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Note note) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(note);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
