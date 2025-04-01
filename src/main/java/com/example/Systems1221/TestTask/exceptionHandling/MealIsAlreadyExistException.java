package com.example.Systems1221.TestTask.exceptionHandling;

public class MealIsAlreadyExistException extends RuntimeException{
    public MealIsAlreadyExistException(String message) {
        super(message);}
}
