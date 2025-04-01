package com.example.Systems1221.TestTask.exceptionHandling;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(String message) {
        super(message);}
}
