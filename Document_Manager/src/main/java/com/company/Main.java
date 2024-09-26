package com.company;

import com.company.repository.DocRepository;
import com.company.shell.CommandShell;
import com.company.exceptions.InvalidMasterDirectoryException;

public class Main {
    public static void main(String[] args) {
        try {
            DocRepository repository = new DocRepository("/path/to/master/directory");
            CommandShell shell = new CommandShell(repository);
            shell.run();
        } catch (InvalidMasterDirectoryException e) {
            System.out.println("Failed to initialize repository: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
