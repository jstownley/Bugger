package com.jonathantownley.bugger.model;

import org.hibernate.SessionFactory;

public class Repository {

    private String name;
    private String description;
    private String databaseFileLocation;

    public Repository(String name, String databaseFileLocation) {
        this.name = name;
        this.databaseFileLocation = databaseFileLocation;
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
}
