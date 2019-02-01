package com.epam.demo.exceptions;

public class CommandFabricException extends RuntimeException{
    public CommandFabricException(final String message) {
        super(message);
    }
}
