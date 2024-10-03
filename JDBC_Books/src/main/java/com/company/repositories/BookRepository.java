package com.company.repositories;

import com.company.models.Book;

public class BookRepository extends AbstractRepo<Book> {

    public BookRepository() {
        super(Book.class);
    }
}