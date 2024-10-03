package com.company;

import com.company.models.Author;
import com.company.models.Book;
import com.company.models.PublishingHouse;
import com.company.repositories.AuthorRepository;
import com.company.repositories.BookRepository;
import com.company.repositories.PublishingHouseRepository;
import com.company.utils.LoggingUtil;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Repositories
        AuthorRepository authorRepo = new AuthorRepository();
        BookRepository bookRepo = new BookRepository();
        PublishingHouseRepository publishingHouseRepo = new PublishingHouseRepository();

        // Step 1: Create and persist a PublishingHouse
        LoggingUtil.log("Creating and saving a publishing house...");
        PublishingHouse penguinHouse = new PublishingHouse("Penguin Books");
        publishingHouseRepo.create(penguinHouse);

        // Step 2: Create and persist Authors
        LoggingUtil.log("Creating and saving authors...");
        Author georgeOrwell = new Author("George Orwell", "English");
        Author aldousHuxley = new Author("Aldous Huxley", "English");
        authorRepo.create(georgeOrwell);
        authorRepo.create(aldousHuxley);

        // Step 3: Create and persist Books with relationships to Authors and PublishingHouse
        LoggingUtil.log("Creating and saving books...");

        Book book1984 = new Book("1984", "1949", 328, Arrays.asList(georgeOrwell), penguinHouse);
        Book braveNewWorld = new Book("Brave New World", "1932", 311, Arrays.asList(aldousHuxley), penguinHouse);

        bookRepo.create(book1984);
        bookRepo.create(braveNewWorld);

        // Step 4: Retrieve data and print results

        // Find authors by name
        LoggingUtil.log("Fetching authors with name 'George':");
        authorRepo.findByName("George").forEach(author -> {
            LoggingUtil.log("Author found: " + author.getName() + " - Language: " + author.getLanguage());
        });

        // Find a book by its ID
        LoggingUtil.log("Fetching book by ID 1:");
        Book foundBook = bookRepo.findById(1);
        if (foundBook != null) {
            LoggingUtil.log("Book found: " + foundBook.getTitle() + " by " + foundBook.getAuthors().get(0).getName() +
                    " published by " + foundBook.getPublishingHouse().getName());
        }

        // Find all books
        LoggingUtil.log("Fetching all books:");
        bookRepo.findAll().forEach(book -> {
            LoggingUtil.log("Book: " + book.getTitle() + ", Authors: " + book.getAuthors().get(0).getName() +
                    ", Publishing House: " + book.getPublishingHouse().getName());
        });

        // Find all publishing houses
        LoggingUtil.log("Fetching all publishing houses:");
        publishingHouseRepo.findAll().forEach(ph -> {
            LoggingUtil.log("Publishing House: " + ph.getName());
        });
    }
}