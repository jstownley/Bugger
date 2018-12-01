package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Severity;
import org.hibernate.SessionFactory;

import java.util.List;

public interface SeverityService {
    List<Severity> findAll(SessionFactory sessionFactory);
    Severity findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Severity severity);
    void delete(SessionFactory sessionFactory, Severity severity);
}
