package com.example.Systems1221.TestTask.repository;

import com.example.Systems1221.TestTask.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<DishEntity, Integer> {
    Optional<DishEntity> findDishByName(String name);
}
