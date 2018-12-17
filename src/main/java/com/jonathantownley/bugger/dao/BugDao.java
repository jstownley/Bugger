package com.jonathantownley.bugger.dao;

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
        // DEBUG: Create some dummy bugs to test the stuff
//        addNewDummyBug(repoName);

        // Open session
        Session session = sessionFactories.get(repoName).openSession();

        // Do stuff
        List<Bug> bugs = session.createQuery("select s from Bug s").getResultList();

        // Close session
        session.close();
        return bugs;
    }

    private void addNewDummyBug(String repoName) {
        Bug bug = new Bug();
        bug.setAuthor("Me");
        bug.setDate(new Date());
        bug.setDescription("This is a dummy bug.");
        bug.setNotes(new ArrayList<Note>());
        bug.setRepositoryName(repoName);
        bug.setSeverity("Minor");
        bug.setStatus("Open");

        Product product = findById(repoName, 1L).getProduct();
        bug.setProduct(product);
        bug.setTitle("Dummy Bug");

        update(repoName, bug);
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
