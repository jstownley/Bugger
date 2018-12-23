package com.jonathantownley.bugger.dao.database;

import com.jonathantownley.bugger.model.Bug;
import com.jonathantownley.bugger.model.Note;
import com.jonathantownley.bugger.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class BugDao extends DAO {

    @Autowired
    private Map<String, SessionFactory> sessionFactories;

    @Override
    public List<Bug> findAll(String repoName) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        List<Bug> bugs = session.createQuery("select s from Bug s").getResultList();

        // Close session
        session.close();
        return bugs;
    }

    @Override
    public Bug findById(String repoName, Long id) {
        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        Bug bug = session.get(Bug.class, id);

        // Close session
        session.close();
        return bug;
    }
}
