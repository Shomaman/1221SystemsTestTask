package com.example.Systems1221.TestTask.service;

import com.example.Systems1221.TestTask.DTO.DishDTO;
import com.example.Systems1221.TestTask.entity.DishEntity;
import com.example.Systems1221.TestTask.exceptionHandling.DishIsAlreadyExistException;
import com.example.Systems1221.TestTask.repository.DishRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {
    private final DishRepository dishRepository;
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    public DishEntity createDish(DishDTO dishDTO) {
        Optional<DishEntity> optionalDish = dishRepository.findDishByName(dishDTO.name());
        if (optionalDish.isPresent()) {
            throw new DishIsAlreadyExistException("Блюдо с таким названием уже существует.");
        }
        return dishRepository.save(createDishEntityFromDishDTO(dishDTO));
    }

    private DishEntity createDishEntityFromDishDTO(DishDTO dishDTO) {
        DishEntity dishEntity = new DishEntity();
        dishEntity.setName(dishDTO.name());
        dishEntity.setCaloriesPerServing(dishDTO.caloriesPerServing());
        dishEntity.setProteins(dishDTO.proteins());
        dishEntity.setFats(dishDTO.fats());
        dishEntity.setCarbohydrates(dishDTO.carbohydrates());
        return dishEntity;
    }
}

