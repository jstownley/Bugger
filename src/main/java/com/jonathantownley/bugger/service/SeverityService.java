package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;
import com.jonathantownley.bugger.model.Severity;

import java.util.List;

public interface SeverityService {
    List<Severity> findAll(Repository repository);
    Severity findById(Repository repository, Long id);
    void update(Repository repository, Severity severity);
    void delete(Repository repository, Severity severity);
}
