package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Status;
import org.hibernate.SessionFactory;

import java.util.List;

public interface StatusService {
    List<Status> findAll(SessionFactory sessionFactory);
    Status findById(SessionFactory sessionFactory, Long id);
    void update(SessionFactory sessionFactory, Status status);
    void delete(SessionFactory sessionFactory, Status status);
}
