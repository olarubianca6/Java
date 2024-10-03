package com.company.models;

import java.util.List;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="books")
@NamedQuery(name = "Author.findByName", query = "SELECT a FROM Book a WHERE a.name LIKE :name")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name="language")
    private String language;

    @Column(name="publication_date")
    private Date publicationDate;

    @Column(name="page_nr")
    private int pages;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    @ManyToOne
    @JoinColumn(name="publishing_house_id", nullable = false)
    private PublishingHouse publishingHouse;

    public Book() {}

    public Book(String title, String language, Date publicationDate, int pages, List<Author> authors, PublishingHouse publishingHouse) {
        this.title = title;
        this.language = language;
        this.publicationDate = publicationDate;
        this.pages = pages;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
    }

    public <T> Book(String number, String number1, int i, List<T> list, PublishingHouse penguinHouse) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getNumberOfPages() {
        return pages;
    }

    public void setNumberOfPages(int pages) {
        this.pages = pages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getLanguage() { return language;}

    public void setLanguage(String language) { this.language = language; }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
