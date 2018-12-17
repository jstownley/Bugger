package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface NoteService {
    List<Note> findAll(String repositoryName);
    Note findById(String repositoryName, Long id);
    void update(String repositoryName, Note note);
    void delete(String repositoryName, Note note);
}