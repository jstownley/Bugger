package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Stage;
import org.hibernate.SessionFactory;

import java.util.List;

public interface StageService {
    List<Stage> findAll(SessionFactory sessionFactory);
    Stage findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Stage stage);
    void delete(SessionFactory sessionFactory, Stage stage);
}
