package com.jonathantownley.bugger.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Bug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String author;
    private String title;
    private String description;
    private String repositoryName;

    @ManyToOne
    private Product product;

    private String severity;
    private String status;

    @OneToMany
    private List<Note> notes;

    // Default constructor for JPA
    public Bug() {}

    public Bug(Date date, String author, String title, String description, String repositoryName, Product product, List<Note> notes, String severity, String status) {
        this.date = date;
        this.author = author;
        this.title = title;
        this.description = description;
        this.repositoryName = repositoryName;
        this.product = product;
        this.notes = notes;
        this.severity = severity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
