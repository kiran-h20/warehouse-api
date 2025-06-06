package com.example.warehouse.exception;

public class ClientNotFoundByApi extends RuntimeException {
    public ClientNotFoundByApi(String message) {
        super(message);
    }
}
