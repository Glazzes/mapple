package com.glaze.maple.expcetion;

public class FieldNotPresentException extends RuntimeException{
    public FieldNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }
}
