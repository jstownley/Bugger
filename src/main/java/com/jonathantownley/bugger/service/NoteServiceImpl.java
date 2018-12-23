package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.dao.database.NoteDao;
import com.jonathantownley.bugger.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteDao noteDao;

    @Override
    public List<Note> findAll(String repositoryName) {
        return noteDao.findAll(repositoryName);
    }

    @Override
    public Note findById(String repositoryName, Long id) {
        return noteDao.findById(repositoryName, id);
    }

    @Override
    public void update(String repositoryName, Note note) {
        noteDao.update(repositoryName, note);
    }

    @Override
    public void delete(String repositoryName, Note note) {
        noteDao.delete(repositoryName, note);
    }
}
