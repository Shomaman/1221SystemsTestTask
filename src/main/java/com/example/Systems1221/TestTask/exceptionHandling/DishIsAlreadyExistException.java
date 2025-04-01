package com.example.Systems1221.TestTask.exceptionHandling;

public class DishIsAlreadyExistException extends RuntimeException{
    public DishIsAlreadyExistException(String message) {
        super(message);}
}
