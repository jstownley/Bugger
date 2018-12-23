package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.dao.database.BugDao;
import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {

    @Autowired
    private BugDao bugDao;

    @Override
    public List<Bug> findAll(String repositoryName) {
        return bugDao.findAll(repositoryName);
    }

    @Override
    public Bug findById(String repositoryName, Long id) {
        return bugDao.findById(repositoryName, id);
    }

    @Override
    public List<Note> findNotesByBugId(String repositoryName, Long id) {
        return findById(repositoryName, id).getNotes();
    }

    @Override
    public Product findProductByBugId(String repositoryName, Long id) {
        return findById(repositoryName, id).getProduct();
    }

    @Override
    public void update(String repositoryName, Bug bug) {
        bugDao.update(repositoryName, bug);
    }

    @Override
    public void delete(String repositoryName, Bug bug) {
        bugDao.delete(repositoryName, bug);
    }
}
