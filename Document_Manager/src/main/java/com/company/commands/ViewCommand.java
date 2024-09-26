package com.company.commands;
import com.company.model.Document;
import java.awt.Desktop;
import java.io.IOException;

public class ViewCommand implements Command {
    private final Document document;

    public ViewCommand(Document document) {
        this.document = document;
    }

    @Override
    public void execute() throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(document.filePath().toFile());
        } else {
            throw new IOException("Desktop is not supported on this platform");
        }
    }
}
