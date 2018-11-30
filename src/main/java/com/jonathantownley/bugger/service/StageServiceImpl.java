package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StageServiceImpl implements StageService {
    @Override
    public List<Stage> findAll(SessionFactory sessionFactory) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        List<Stage> stages = session.createQuery("select s from Stage s").getResultList();

        // Close session
        session.close();
        return stages;
    }

    @Override
    public Stage findById(SessionFactory sessionFactory, Long id) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        Stage stage = session.get(Stage.class, id);

        // Close session
        session.close();
        return stage;
    }

    @Override
    public void update(SessionFactory sessionFactory, Stage stage) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.saveOrUpdate(stage);
        session.getTransaction().commit();

        // Close session
        session.close();
    }

    @Override
    public void delete(SessionFactory sessionFactory, Stage stage) {
        // Open session
        Session session = sessionFactory.openSession();

        // Do stuff
        session.beginTransaction();
        session.delete(stage);
        session.getTransaction().commit();

        // Close session
        session.close();
    }
}
