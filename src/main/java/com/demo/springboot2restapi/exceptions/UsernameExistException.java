package com.demo.springboot2restapi.exceptions;

public class UsernameExistException extends Exception {

    public UsernameExistException(String message) {
        super(message);
    }
}
