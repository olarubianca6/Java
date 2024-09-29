package com.company.models;

import java.util.List;
import java.util.Date;

public class Book {
    private int id;
    private String title;
    private String language;
    private Date publicationDate;
    private int pages;
    private List<Author> authors;

    public Book(int id, String title, String language, Date publicationDate, int pages, List<Author> authors) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.authors = authors;
    }

    // Getters and Setters for all fields
}
