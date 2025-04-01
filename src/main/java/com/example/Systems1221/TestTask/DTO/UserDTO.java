package com.example.Systems1221.TestTask.DTO;


import jakarta.validation.constraints.*;

public record UserDTO(

        @NotBlank(message = "укажите имя пользователя")
        @Size(min = 2, max = 50, message = "имя должно быть от {min} до {max} символов")
        String name,

        @NotBlank(message = "укажите email")
        @Email(message = "введите правильный email")
        String email,

        @NotNull(message = "Укажите возраст")
        @Positive(message = "Возраст должен быть больше 0")
        @Max(message = "Возраст не может быть больше 120", value = 121)
        Integer age,

        @NotNull(message = "Укажите вес")
        @Positive(message = "Вес должен быть больше 0")
        @Max(message = "Вес не может быть больше 1000", value = 1001)
        Double weight,

        @NotNull(message = "Укажите рост")
        @Positive(message = "Рост должен быть больше 0")
        @Max(message = "Рост не может быть больше 300", value = 301)
        Double height,

        @NotBlank(message = "укажите цель")
        String target)

{}
