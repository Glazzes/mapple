package com.glaze.maple.expcetion;

public class ConstructorNotFoundException extends RuntimeException{
    public ConstructorNotFoundException(String message) {
        super(message);
    }
}
