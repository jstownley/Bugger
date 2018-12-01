package com.jonathantownley.bugger.model;

import org.hibernate.SessionFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Repository {

    private String name;
    private String description;
    private String databaseFileLocation;
    private SessionFactory sessionFactory;

    public Repository(String name, String databaseFileLocation, SessionFactory sessionFactory) {
        this.name = name;
        this.databaseFileLocation = databaseFileLocation;
        this.sessionFactory = sessionFactory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatabaseFileLocation() {
        return databaseFileLocation;
    }

    public void setDatabaseFileLocation(String databaseFileLocation) {
        this.databaseFileLocation = databaseFileLocation;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
