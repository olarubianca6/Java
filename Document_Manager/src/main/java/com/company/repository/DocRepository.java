package com.company.repository;

import com.company.model.Person;
import com.company.model.Document;
import com.company.exceptions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class DocRepository {
    private final Path masterDir;

    public DocRepository(String masterDirPath) throws InvalidMasterDirectoryException {
        this.masterDir = Paths.get(masterDirPath);
        if (!Files.isDirectory(masterDir)) {
            throw new InvalidMasterDirectoryException("The specified master directory does not exist.");
        }
    }

    public void createPersonDir(Person person) throws IOException, PersonAlreadyExistsException {
        Path personDir = masterDir.resolve(person.uniqueId());
        if (!Files.exists(personDir)) {
            Files.createDirectories(personDir);
        } else {
            throw new PersonAlreadyExistsException("Person directory already exists for " + person.uniqueId());
        }
    }

    public List<Person> listAllEmployees() throws IOException {
        List<Person> employees = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(masterDir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    employees.add(new Person(entry.getFileName().toString(), entry.getFileName().toString()));
                }
            }
        }
        return employees;
    }

    public List<Document> listDocuments(Person person) throws IOException, PersonNotFoundException{
        Path personDir = masterDir.resolve(person.uniqueId());
        if (!Files.exists(personDir)) {
            throw new PersonNotFoundException("Person Directory not found for " + person.uniqueId());
        }

        List<Document> documents = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(personDir)) {
            for (Path entry : stream) {
                documents.add(new Document(entry.getFileName().toString(), entry));
            }
        }

        return documents;
    }

    public Path getDocumentPath(String docName) {
        try (DirectoryStream<Path> employeeDirs = Files.newDirectoryStream(masterDir)) {
            for (Path employeeDir : employeeDirs) {
                if (Files.isDirectory(employeeDir)) {
                    Path docPath = employeeDir.resolve(docName);

                    if (Files.exists(docPath)) {
                        return docPath;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
