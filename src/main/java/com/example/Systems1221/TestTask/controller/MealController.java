package com.example.Systems1221.TestTask.controller;

import com.example.Systems1221.TestTask.DTO.MealDTO;
import com.example.Systems1221.TestTask.entity.MealEntity;
import com.example.Systems1221.TestTask.service.MealService;
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
@RequestMapping("/api/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMeal(@Valid @RequestBody MealDTO mealDTO,
                                        BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        MealEntity mealEntity = mealService.createMeal(mealDTO);
        return ResponseEntity.ok().body(mealEntity);
    }
}
