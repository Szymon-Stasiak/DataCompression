package org.example.exceptions;

public class SequencesCantBeLessThanZeroException extends RuntimeException {
    public SequencesCantBeLessThanZeroException(String message) {
        super(message);
    }
}
