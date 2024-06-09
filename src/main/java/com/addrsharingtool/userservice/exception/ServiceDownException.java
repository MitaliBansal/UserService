package com.addrsharingtool.userservice.exception;

public class ServiceDownException extends RuntimeException {

    public ServiceDownException() {}

    public ServiceDownException(String message) {
        super(message);
    }

}