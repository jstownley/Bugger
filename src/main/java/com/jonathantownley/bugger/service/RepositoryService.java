package com.jonathantownley.bugger.service;

import com.jonathantownley.bugger.model.Repository;

import java.util.List;

public interface RepositoryService {
    List<Repository> getRepositories();
    Repository getRepository(String repositoryName);
    List<String> getRepositoryNames();
    void addRepository(Repository repository);
}
