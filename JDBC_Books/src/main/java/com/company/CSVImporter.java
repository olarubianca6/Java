package com.company;

import com.company.daos.BookDAO;
import com.company.models.Author;
import com.company.models.Book;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVImporter {

    private BookDAO bookDAO;

    public CSVImporter() {
        this.bookDAO = new BookDAO();
    }

    public void importBooks(String csvFilePath) {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = csvReader.readAll();

            records.remove(0);

            for (String[] record : records) {
                Book book = mapCsvRecordToBook(record);
                if (book != null) {
                    bookDAO.addBook(book);
                    System.out.println("Inserted book: " + book.getTitle());
                }
            }

            System.out.println("Data import completed successfully!");

        } catch (IOException | CsvException | SQLException | ParseException e) {
            System.err.println("Error during data import: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Book mapCsvRecordToBook(String[] record) throws ParseException {
        if (record.length < 6) {
            System.err.println("Invalid record: " + String.join(",", record));
            return null;
        }

        String title = record[1];
        String authorNames = record[2];
        String language = record[3];
        int pages = Integer.parseInt(record[4]);
        Date publicationDate = new SimpleDateFormat("yyyy-MM-dd").parse(record[5]);
        
        List<Author> authors = parseAuthors(authorNames);

        return new Book(title, language, publicationDate, pages, authors);
    }

    private List<Author> parseAuthors(String authorNames) {
        String[] authorArray = authorNames.split(",");
        List<Author> authors = new ArrayList<>();
        for (String authorName : authorArray) {
            Author author = new Author(authorName.trim());
            authors.add(author);
        }
        return authors;
    }

    public static void main(String[] args) {
        String csvFilePath = "path/to/your/books.csv";
        CSVImporter csvImporter = new CSVImporter();
        csvImporter.importBooks(csvFilePath);
    }
}
