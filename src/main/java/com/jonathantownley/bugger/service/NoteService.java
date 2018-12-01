package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface NoteService {
    List<Note> findAll(Repository repository);
    Note findById(Repository repository, Long id);
    void update(Repository repository, Note note);
    void delete(Repository repository, Note note);
}
