package com.company.shell;

import com.company.commands.Command;
import com.company.commands.ExportCommand;
import com.company.commands.ViewCommand;
import com.company.exceptions.InvalidCommandException;
import com.company.repository.DocRepository;
import com.company.model.Document;

import java.util.Scanner;

public class CommandShell {
    private final DocRepository repository;

    public CommandShell(DocRepository repository) {
        this.repository = repository;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter command: ");
            String commandInput = scanner.nextLine();
            try {
                Command command = parseCommand(commandInput);
                command.execute();
            } catch(InvalidCommandException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Command parseCommand(String commandInput) throws InvalidCommandException {
        String[] parts = commandInput.split(" ");
        switch (parts[0]) {
            case "view":
                Document doc = new Document(parts[1], repository.getDocumentPath(parts[1]));
                return new ViewCommand(doc);
            case "export":
                return new ExportCommand(repository, parts[1]);
            default:
                throw new InvalidCommandException("Unknown command: " + parts[0]);
        }
    }
}
