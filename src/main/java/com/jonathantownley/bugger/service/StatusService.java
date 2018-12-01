package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.model.Status;

import java.util.List;

public interface StatusService {
    List<Status> findAll(Repository repository);
    Status findById(Repository repository, Long id);
    void update(Repository repository, Status status);
    void delete(Repository repository, Status status);
}
