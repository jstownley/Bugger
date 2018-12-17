package com.jonathantownley.bugger.dao;

import com.jonathantownley.bugger.model.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NoteDao extends DAO {

    @Autowired
    private Map<String, SessionFactory> sessionFactories;

    @Override
    public List<Note> findAll(String repoName) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        List<Note> notes = session.createQuery("select n from Note n").getResultList();

        // Close session
        session.close();
        return notes;
    }

    @Override
    public Note findById(String repoName, Long id) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        Note note = session.get(Note.class, id);

        // Close session
        session.close();
        return note;
    }
}
