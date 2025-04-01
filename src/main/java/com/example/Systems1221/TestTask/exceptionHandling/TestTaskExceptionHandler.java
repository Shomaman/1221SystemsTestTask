package com.example.Systems1221.TestTask.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class TestTaskExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List<String>> handleBindException(BindException exception) {
        return ResponseEntity.badRequest().body(exception.getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .toList());
    }

    @ExceptionHandler(MealIsAlreadyExistException.class)
    public ResponseEntity<List<String>> handleMealIsAlreadyExistException(MealIsAlreadyExistException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DishIsAlreadyExistException.class)
    public ResponseEntity<List<String>> handleDishIsAlreadyExistException(DishIsAlreadyExistException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DishNotFoundException.class)
    public ResponseEntity<List<String>> handleDishNotFoundException(DishNotFoundException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserIsAlreadyExistException.class)
    public ResponseEntity<List<String>> handleUserIsAlreadyExistException(UserIsAlreadyExistException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<List<String>> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
