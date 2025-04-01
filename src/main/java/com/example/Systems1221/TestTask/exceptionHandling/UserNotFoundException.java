package com.example.Systems1221.TestTask.exceptionHandling;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);}
}
