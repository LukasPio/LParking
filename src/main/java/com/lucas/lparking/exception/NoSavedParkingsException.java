package com.lucas.lparking.exception;

public class NoSavedParkingsException extends RuntimeException {
    public NoSavedParkingsException() {
        super("No saved parking");
    }
}
