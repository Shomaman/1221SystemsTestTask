package com.example.Systems1221.TestTask.service;

import com.example.Systems1221.TestTask.DTO.MealDTO;
import com.example.Systems1221.TestTask.entity.DishEntity;
import com.example.Systems1221.TestTask.entity.MealEntity;
import com.example.Systems1221.TestTask.entity.UserEntity;
import com.example.Systems1221.TestTask.exceptionHandling.DishNotFoundException;
import com.example.Systems1221.TestTask.exceptionHandling.MealIsAlreadyExistException;
import com.example.Systems1221.TestTask.exceptionHandling.UserNotFoundException;
import com.example.Systems1221.TestTask.repository.DishRepository;
import com.example.Systems1221.TestTask.repository.MealRepository;
import com.example.Systems1221.TestTask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MealService {
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public MealService(MealRepository mealRepository, UserRepository userRepository, DishRepository dishRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    public MealEntity createMeal(MealDTO mealDTO) {

        return mealRepository.save(createMealEntityFromMealDTO(mealDTO));
    }

    private MealEntity createMealEntityFromMealDTO(MealDTO mealDTO) {
        MealEntity mealEntity = new MealEntity();
        Optional<UserEntity> userByEmail = userRepository.findUserByEmail(mealDTO.userEmail());
        if (userByEmail.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        LocalDateTime createdAt = mealDTO.mealTime();
        Optional<MealEntity> mealByMealTime = mealRepository.findMealEntityByMealTime(createdAt);
        if (mealByMealTime.isPresent()) {
            throw new MealIsAlreadyExistException("Прием пищи в такое время уже был создан");
        }
        mealEntity.setUserEmail(mealDTO.userEmail());
        mealEntity.setName(mealDTO.name());
        List<DishEntity> dishEntities = mealDTO.listOfDishes();
        for (DishEntity dishEntity : dishEntities) {
            Optional<DishEntity> dishEntityOpt = dishRepository.findDishByName(dishEntity.getName());
            if (dishEntityOpt.isEmpty()) {
                throw new DishNotFoundException("Такое блюдо не найдено");
            }

            mealEntity.addDishToListOfDishes(dishEntity);
        }
        mealEntity.setMealTime(mealDTO.mealTime());
        return mealEntity;
    }
}
