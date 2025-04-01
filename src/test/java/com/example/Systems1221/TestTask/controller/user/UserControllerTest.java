package com.example.Systems1221.TestTask.controller.user;

import com.example.Systems1221.TestTask.DTO.UserDTO;
import com.example.Systems1221.TestTask.controller.UserController;
import com.example.Systems1221.TestTask.entity.UserEntity;
import com.example.Systems1221.TestTask.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;

import java.util.List;
import java.util.Map;

import static com.example.Systems1221.TestTask.entity.UserTarget.MAINTAIN_WEIGHT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserService service;

    @InjectMocks
    UserController controller;

    @Test
    void createUserWithValidUserDTO_ReturnsUserEntity() throws BindException {
        //given
        var userDTO = new UserDTO("Вася", "vasya@mail.ru", 35, 60d, 180d,
                "MAINTAIN_WEIGHT");
        var bindingResult = new MapBindingResult(Map.of(), "userDTO");
        var basicCalorieRequirements = 1500d;
        doReturn(new UserEntity("Вася", "vasya@mail.ru", 35, 60d, 180d,
                MAINTAIN_WEIGHT,basicCalorieRequirements))
                .when(this.service).createUser(userDTO);
        //when
        var result = this.controller.createUser(userDTO, bindingResult);
        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new UserEntity("Вася", "vasya@mail.ru", 35, 60d, 180d,
                MAINTAIN_WEIGHT,basicCalorieRequirements), result.getBody());
        verify(this.service).createUser(userDTO);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    void createUserWithInvalidUserDTO_ThrowsBindException() {
        //given
        var userDTO = new UserDTO(null, "vasya@mail.ru", 35, 60d, 180d,
                "MAINTAIN_WEIGHT");
        var bindingResult = new MapBindingResult(Map.of(), "userDTO");
        bindingResult.addError(new FieldError("userDTO", "name", "error"));

        // when
        var exception = assertThrows(BindException.class,
                () -> this.controller.createUser(userDTO, bindingResult));

        // then
        assertEquals(List.of(new FieldError("userDTO", "name", "error")),
                exception.getAllErrors());
        verifyNoInteractions(this.service);
    }
}