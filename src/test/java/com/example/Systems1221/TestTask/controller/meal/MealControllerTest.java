package com.example.Systems1221.TestTask.controller.meal;

import com.example.Systems1221.TestTask.DTO.MealDTO;
import com.example.Systems1221.TestTask.controller.MealController;
import com.example.Systems1221.TestTask.entity.DishEntity;
import com.example.Systems1221.TestTask.entity.MealEntity;
import com.example.Systems1221.TestTask.service.MealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MealControllerTest {
    @Mock
    MealService service;

    @InjectMocks
    MealController controller;

    @Test
    void createMealWithValidMealDTO_ReturnsMealEntity() throws BindException {
        //given
        var listOfDishes = List.of(new DishEntity("Тирамису", 180d, 5d, 16d, 21d));
        var mealDTO = new MealDTO("test@mail.com", "Завтрак", listOfDishes,
                LocalDateTime.of(2025, 3,25,11, 0,0));
        var bindingResult = new MapBindingResult(Map.of(), "mealDTO");
        doReturn(new MealEntity("test@mail.com", "Завтрак", listOfDishes,
                LocalDateTime.of(2025,3,25,11,0,0)))
                .when(this.service).createMeal(mealDTO);
        //when
        var result = this.controller.createMeal(mealDTO, bindingResult);
        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new MealEntity("test@mail.com", "Завтрак", listOfDishes,
                LocalDateTime.of(2025,3,25,11,0,0)), result.getBody());
        verify(this.service).createMeal(mealDTO);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    void createMealWithInvalidMealDTO_ThrowsBindException() {
        //given
        var listOfDishes = List.of(new DishEntity("Тирамису", 180d, 5d, 16d, 21d));
        var mealDTO = new MealDTO("test@mail.com", null, listOfDishes,
                LocalDateTime.of(2025,3,25,11,0,0));
        var bindingResult = new MapBindingResult(Map.of(), "mealDTO");
        bindingResult.addError(new FieldError("mealDTO", "name", "error"));

        // when
        var exception = assertThrows(BindException.class,
                () -> this.controller.createMeal(mealDTO, bindingResult));

        // then
        assertEquals(List.of(new FieldError("mealDTO", "name", "error")),
                exception.getAllErrors());
        verifyNoInteractions(this.service);
    }
}