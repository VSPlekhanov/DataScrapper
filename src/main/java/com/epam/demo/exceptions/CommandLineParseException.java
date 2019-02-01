package com.epam.demo.exceptions;

public class CommandLineParseException extends RuntimeException {
    public CommandLineParseException(final String message) {
        super(message);
    }
}
