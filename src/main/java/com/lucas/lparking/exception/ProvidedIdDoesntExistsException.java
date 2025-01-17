package com.lucas.lparking.exception;

public class ProvidedIdDoesntExistsException extends RuntimeException {
    public ProvidedIdDoesntExistsException() {
        super("Provided id does not exists");
    }
}
