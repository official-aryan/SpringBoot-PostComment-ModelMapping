package com.example.ExceptionHandler;

import org.aspectj.bridge.IMessage;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
