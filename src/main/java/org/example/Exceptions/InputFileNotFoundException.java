package org.example.Exceptions;

public class InputFileNotFoundException extends RuntimeException {
    public InputFileNotFoundException(String message) {
        super(message);
    }
}
