package com.example.Systems1221.TestTask.DTO;


import com.example.Systems1221.TestTask.entity.DishEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record MealDTO(

        @NotBlank(message = "укажите email пользователя")
        @Email(message = "введите правильный email")
        String userEmail,

        @NotBlank(message = "укажите название приема пищи")
        @Size(min = 2, max = 50, message = "название приема пищи должно быть от {min} до {max} символов")
        String name,

        @NotEmpty(message = "заполните список блюд")
        List<DishEntity> listOfDishes,

        @NotNull(message = "введите дату приема пищи")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime mealTime)
        {
        }
