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

    @ManyToOne
    private Author author;
    private String title;
    private String description;
    private String repositoryName;

    @ManyToOne
    private Product product;

    @OneToMany
    private List<Note> notes;

    @ManyToOne
    private Stage stage;

    // Default constructor for JPA
    public Bug() {}

    public Bug(Date date, Author author, String title, String description, String repositoryName, Product product, List<Note> notes, Stage stage) {
        this.date = date;
        this.author = author;
        this.title = title;
        this.description = description;
        this.repositoryName = repositoryName;
        this.product = product;
        this.notes = notes;
        this.stage = stage;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
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

    public String getRepo() {
        return repositoryName;
    }

    public void setRepo(String repositoryName) {
        this.repositoryName = repositoryName;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
