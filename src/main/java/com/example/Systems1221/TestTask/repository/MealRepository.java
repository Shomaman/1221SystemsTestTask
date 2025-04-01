package com.example.Systems1221.TestTask.repository;

import com.example.Systems1221.TestTask.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer> {
    Optional<MealEntity> findMealEntityByMealTime(LocalDateTime mealTime);
}
