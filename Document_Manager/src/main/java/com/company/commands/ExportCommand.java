package com.company.commands;
import com.company.repository.DocRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class ExportCommand implements Command {
    private final DocRepository repository;
    private final String exportPath;

    public ExportCommand(DocRepository repository, String exportPath) {
        this.repository = repository;
        this.exportPath = exportPath;
    }

    @Override
    public void execute() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(exportPath);
        mapper.writeValue(file, repository.listAllEmployees());
    }
}