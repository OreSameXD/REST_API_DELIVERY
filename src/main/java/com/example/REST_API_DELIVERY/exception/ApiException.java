package com.example.REST_API_DELIVERY.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
