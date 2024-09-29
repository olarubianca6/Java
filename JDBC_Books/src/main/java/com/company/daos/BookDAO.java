package com.company.daos;

import com.company.DatabaseConnection;
import com.company.models.Author;
import com.company.models.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) throws SQLException {
        String insertBookQuery = "INSERT INTO books (title, language, publication_date, pages) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement bookStmt = conn.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS)) {

            bookStmt.setString(1, book.getTitle());
            bookStmt.setString(2, book.getLanguage());
            bookStmt.setDate(3, new java.sql.Date(book.getPublicationDate().getTime()));
            bookStmt.setInt(4, book.getPages());

            bookStmt.executeUpdate();

            ResultSet rs = bookStmt.getGeneratedKeys();
            if (rs.next()) {
                int bookId = rs.getInt(1);

                for (Author author : book.getAuthors()) {
                    linkBookWithAuthor(bookId, author.getId());
                }
            }
        }
    }

    private void linkBookWithAuthor(int bookId, int authorId) throws SQLException {
        String linkQuery = "INSERT INTO book_authors (book_id, author_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement linkStmt = conn.prepareStatement(linkQuery)) {

            linkStmt.setInt(1, bookId);
            linkStmt.setInt(2, authorId);
            linkStmt.executeUpdate();
        }
    }

    public Book getBookById(int id) throws SQLException {
        String selectBookQuery = "SELECT * FROM books WHERE id = ?";
        Book book = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement bookStmt = conn.prepareStatement(selectBookQuery)) {

            bookStmt.setInt(1, id);
            ResultSet rs = bookStmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String language = rs.getString("language");
                Date publicationDate = rs.getDate("publication_date");
                int pages = rs.getInt("pages");

                List<Author> authors = getAuthorsByBookId(id);

                book = new Book(id, title, language, publicationDate, pages, authors);
            }
        }

        return book;
    }

    public List<Book> getAllBooks() throws SQLException {
        String selectAllBooksQuery = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllBooksQuery)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String language = rs.getString("language");
                Date publicationDate = rs.getDate("publication_date");
                int pages = rs.getInt("pages");

                List<Author> authors = getAuthorsByBookId(id);

                books.add(new Book(id, title, language, publicationDate, pages, authors));
            }
        }

        return books;
    }

    public void deleteBook(int id) throws SQLException {
        String deleteBookQuery = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteBookQuery)) {

            deleteStmt.setInt(1, id);
            deleteStmt.executeUpdate();
        }
    }

    private List<Author> getAuthorsByBookId(int bookId) throws SQLException {
        String selectAuthorsQuery =
                "SELECT a.id, a.name FROM authors a " +
                        "JOIN book_authors ba ON a.id = ba.author_id " +
                        "WHERE ba.book_id = ?";

        List<Author> authors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement authorStmt = conn.prepareStatement(selectAuthorsQuery)) {

            authorStmt.setInt(1, bookId);
            ResultSet rs = authorStmt.executeQuery();

            while (rs.next()) {
                int authorId = rs.getInt("id");
                String name = rs.getString("name");
                authors.add(new Author(authorId, name));
            }
        }

        return authors;
    }
}
