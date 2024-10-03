package com.company.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="authors")
@NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name LIKE :name")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name="language")
    String language;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String language) {
    }

    public Author(int id, String name) {
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


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {}
}
