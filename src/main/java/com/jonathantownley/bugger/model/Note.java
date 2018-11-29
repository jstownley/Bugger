package com.jonathantownley.bugger.model;

import java.util.Date;

public class Note {

    private Long id;
    private String content;
    private Date date;
    private Author author;

    public Note(Long id, String content, Date date, Author author) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
