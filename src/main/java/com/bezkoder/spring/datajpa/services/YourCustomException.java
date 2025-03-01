package com.bezkoder.spring.datajpa.services;

public class YourCustomException extends RuntimeException {
    public YourCustomException(String message) {
        super(message);
    }

    public YourCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
