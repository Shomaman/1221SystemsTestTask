package com.example.Systems1221.TestTask.controller.dish;

import com.example.Systems1221.TestTask.DTO.DishDTO;
import com.example.Systems1221.TestTask.controller.DishController;
import com.example.Systems1221.TestTask.entity.DishEntity;
import com.example.Systems1221.TestTask.service.DishService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishControllerTest {
    @Mock
    DishService service;

    @InjectMocks
    DishController controller;

    @Test
    void createDishWithValidDishDTO_ReturnsDishEntity() throws BindException {
        //given
        var dishDTO = new DishDTO("Тирамису", 180d, 5d, 16d, 21d);
        var bindingResult = new MapBindingResult(Map.of(), "dishDTO");
        doReturn(new DishEntity("Тирамису", 180d, 5d, 16d, 21d))
                .when(this.service).createDish(dishDTO);
        //when
        var result = this.controller.createDish(dishDTO, bindingResult);
        //then
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(new DishEntity("Тирамису", 180d, 5d, 16d, 21d), result.getBody());
        verify(this.service).createDish(dishDTO);
        verifyNoMoreInteractions(this.service);
    }

    @Test
    void createDishWithInvalidDishDTO_ThrowsBindException() {
        //given
        var dishDTO = new DishDTO(null, 180d, 5d, 16d, 21d);
        var bindingResult = new MapBindingResult(Map.of(), "dishDTO");
        bindingResult.addError(new FieldError("dishDTO", "name", "error"));

        // when
        var exception = assertThrows(BindException.class,
                () -> this.controller.createDish(dishDTO, bindingResult));

        // then
        assertEquals(List.of(new FieldError("dishDTO", "name", "error")),
                exception.getAllErrors());
        verifyNoInteractions(this.service);
    }
}