package com.example.Systems1221.TestTask.service;

import com.example.Systems1221.TestTask.DTO.UserDTO;
import com.example.Systems1221.TestTask.entity.UserTarget;
import com.example.Systems1221.TestTask.entity.UserEntity;
import com.example.Systems1221.TestTask.exceptionHandling.UserIsAlreadyExistException;
import com.example.Systems1221.TestTask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserDTO userDTO) {
        Optional<UserEntity> optionalUser = userRepository.findUserByEmail(userDTO.email());
        if (optionalUser.isPresent()) {
            throw new UserIsAlreadyExistException("Пользователь с таким email уже существует.");
        }
        return userRepository.save(createUserEntityFromUserDTO(userDTO));
    }

    private UserEntity createUserEntityFromUserDTO(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.name());
        userEntity.setEmail(userDTO.email());
        userEntity.setAge(userDTO.age());
        userEntity.setWeight(userDTO.weight());
        userEntity.setHeight(userDTO.height());
        userEntity.setTarget(UserTarget.valueOf(userDTO.target()));
        Double basicCalorieRequirements = 447.593 + (9.247 * userDTO.weight()) + (3.098 * userDTO.height())
                - (4.330 * userDTO.age());
        userEntity.setBasicCalorieRequirements(basicCalorieRequirements);
        return userEntity;
    }
}

