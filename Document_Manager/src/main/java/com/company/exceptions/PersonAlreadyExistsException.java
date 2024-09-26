package com.company.exceptions;

public class PersonAlreadyExistsException extends Exception{
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
