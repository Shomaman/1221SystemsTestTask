package com.example.Systems1221.TestTask.controller;

import com.example.Systems1221.TestTask.DTO.DishDTO;
import com.example.Systems1221.TestTask.entity.DishEntity;
import com.example.Systems1221.TestTask.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createDish(@Valid @RequestBody DishDTO dishDTO,
                                     BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        DishEntity dishEntity = dishService.createDish(dishDTO);
        return ResponseEntity.ok().body(dishEntity);
    }
}
