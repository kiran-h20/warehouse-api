package com.example.warehouse.exception;

public class UserNotLoggedInException extends  RuntimeException {
    public UserNotLoggedInException(String message) {
        super(message);
    }
}
