package com.example.couponsystem.customExceptions;

public class ServiceException extends RuntimeException{
    public ServiceException(String message) {
        super(message);
    }
}
