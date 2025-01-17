package com.lucas.lparking.exception;

public class CnpjAlreadyExistsException extends RuntimeException{
    public CnpjAlreadyExistsException() {
        super("CNPJ already exists");
    }
}
