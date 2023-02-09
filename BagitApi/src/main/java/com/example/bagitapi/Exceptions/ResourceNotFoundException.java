package com.example.bagitapi.Exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String name) {
        super(name);
    }

}
