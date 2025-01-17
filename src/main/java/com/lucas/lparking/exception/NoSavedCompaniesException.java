package com.lucas.lparking.exception;

public class NoSavedCompaniesException extends RuntimeException {
    public NoSavedCompaniesException() {
        super("No saved companies");
    }
}
