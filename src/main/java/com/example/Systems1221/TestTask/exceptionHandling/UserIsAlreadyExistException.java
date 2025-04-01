package com.example.Systems1221.TestTask.exceptionHandling;

public class UserIsAlreadyExistException extends RuntimeException{
    public UserIsAlreadyExistException(String message) {
        super(message);}
}
