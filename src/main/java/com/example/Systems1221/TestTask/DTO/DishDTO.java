package com.example.Systems1221.TestTask.DTO;


import jakarta.validation.constraints.*;

public record DishDTO(

        @NotBlank(message = "Укажите название блюда")
        @Size(min = 2, max = 200, message = "название блюда должно быть от {min} до {max} символов")
        String name,

        @NotNull(message = "Укажите количество калорий на порцию")
        @Positive(message = "Количество калорий на порцию должно быть больше 0")
        @Max(message = "Количество калорий на порцию не может быть больше 100000", value = 100000)
        Double caloriesPerServing,

        @NotNull(message = "Укажите количество белков")
        @Positive(message = "Количество белков должно быть больше 0")
        @Max(message = "Количество белков не может быть больше 100000", value = 100000)
        Double proteins,

        @NotNull(message = "Укажите количество жиров")
        @Positive(message = "Количество жиров должно быть больше 0")
        @Max(message = "Количество жиров не может быть больше 100000", value = 100000)
        Double fats,

        @NotNull(message = "Укажите количество углеводов")
        @Positive(message = "Количество углеводов должно быть больше 0")
        @Max(message = "Количество углеводов не может быть больше 100000", value = 100000)
        Double carbohydrates) {
}
