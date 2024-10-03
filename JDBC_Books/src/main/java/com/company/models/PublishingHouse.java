package com.company.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="publishing_houses")
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
    private List<Book> books;

    public PublishingHouse() {}

    public PublishingHouse(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
