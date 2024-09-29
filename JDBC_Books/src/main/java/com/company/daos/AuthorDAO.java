package com.company.daos;

import com.company.DatabaseConnection;
import com.company.models.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    public void addAuthor(String name) throws SQLException {
        String query = "INSERT INTO authors (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    public Author getAuthorById(int id) throws SQLException {
        String query = "SELECT * FROM authors WHERE id = ?";
        Author author = null;

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                author = new Author(result.getInt("id"), result.getString("name"));
            }
        }
        return author;
    }

    public List<Author> getAllAuthors() throws SQLException {
        String query = "SELECT * FROM authors";
        List<Author> authors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query)) {

            while (result.next()) {
                authors.add(new Author(result.getInt("id"), result.getString("name")));
            }
        }
        return authors;
    }

    public void deleteAuthor(int id) throws SQLException {
        String query = "DELETE FROM authors WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
