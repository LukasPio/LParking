package com.lucas.lparking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(value = CnpjAlreadyExistsException.class)
    public ResponseEntity<String> cnpjAlreadyExistsException() {
        return new ResponseEntity<>("CNPJ already exists", HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = ProvidedIdDoesntExistsException.class)
    public ResponseEntity<String> providedIdDoesntExistsException() {
        return new ResponseEntity<>("Provided id does not exists", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = NoSavedCompaniesException.class)
    public ResponseEntity<String> noSavedCompaniesException() {
        return new ResponseEntity<>("No saved companies exists", HttpStatus.NO_CONTENT);
    }
}
