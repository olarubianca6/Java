package com.company.commands;
import com.company.exceptions.InvalidCommandException;

public interface Command {
    void execute() throws InvalidCommandException, Exception;
}
