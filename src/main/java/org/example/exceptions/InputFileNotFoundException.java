package org.example.exceptions;

public class InputFileNotFoundException extends RuntimeException {
    public InputFileNotFoundException(String message) {
        super(message);
    }
}
